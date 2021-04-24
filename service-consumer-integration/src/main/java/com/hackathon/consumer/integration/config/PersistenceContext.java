package com.hackathon.consumer.integration.config;

import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.dialect.MySQL5Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Akshay
 *
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hackathon.consumer.integration.repository",entityManagerFactoryRef="consumerDataStoreEM", transactionManagerRef="consumerDataStoreTM")
public class PersistenceContext {

	private final Logger log = LoggerFactory.getLogger(PersistenceContext.class);
	
    protected static final String PROPERTY_NAME_DATABASE_DRIVER = "hackathon.consumer.db.driver";
    protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "hackathon.consumer.db.password";
    protected static final String PROPERTY_NAME_DATABASE_URL = "hackathon.consumer.db.url";
    protected static final String PROPERTY_NAME_DATABASE_USERNAME = "hackathon.consumer.db.username";
    protected static final String PROPERTY_NAME_DATABASE_MAX_CONNECTIONS = "hackathon.consumer.db.maxconnections";
    protected static final String PROPERTY_NAME_DATABASE_MIN_CONNECTIONS = "hackathon.consumer.db.minconnections";
    protected static final String PROPERTY_NAME_DATABASE_MAX_PARTITIONS = "hackathon.consumer.db.maxpartitions";
    protected static final String PROPERTY_NAME_DATABASE_MAX_LIFETIME="hackathon.consumer.db.maxlifetimeinmillis";
	protected static final String PROPERTY_NAME_DATABASE_CONNECTION_TIMEOUT="hackathon.consumer.db.connectiontimeoutinmillis";
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";	
    //private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_LAZY_LOAD = "hibernate.enable_lazy_load_no_trans";

    private static final String PROPERTY_PACKAGES_TO_SCAN = "com.hackathon.consumer.integration.domain";

    @Autowired
    private Environment environment;
  
    @Bean(name="consumerDataStore")
	@Primary
	public DataSource dataSource() throws IllegalStateException, NamingException { 
    	log.info("Initiate retailDataStore instance");
    	
    	/**
		 * Set custom data source properties 
		 */
		
    	HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setJdbcUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		//dataSource.setConnectionTestQuery("SELECT SMTP_CODE FROM JN_C2_AM_SMTP");
		
		dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty(PROPERTY_NAME_DATABASE_MAX_CONNECTIONS)));
		dataSource.setMaxLifetime(Long.parseLong(environment.getProperty(PROPERTY_NAME_DATABASE_MAX_LIFETIME)));
		dataSource.setConnectionTimeout(Long.parseLong(environment.getProperty(PROPERTY_NAME_DATABASE_CONNECTION_TIMEOUT)));
	
		//Test query
		dataSource.setConnectionTestQuery("SELECT 1");
		log.info(" retailDataStore instance created");
		return dataSource;
    }

    @Bean(name="consumerDataStoreTM")
    @Primary
    public JpaTransactionManager transactionManager() throws IllegalStateException, NamingException, SQLException {
    	log.info("Initiate consumerDataStoreTM instance");
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        log.info("consumerDataStoreTM instance created");
        return transactionManager;
    }

    @Bean(name="consumerDataStoreEM")
    @DependsOn("consumerDataStore")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IllegalStateException, NamingException, SQLException  {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        log.info("Initiate DataStoreEM instance");
        DataSource dataSource = dataSource();
		entityManagerFactoryBean.setDataSource(dataSource);
        
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);

        Map<String, Object> jpaProperties = entityManagerFactoryBean.getJpaPropertyMap();

        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));

        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_LAZY_LOAD, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_LAZY_LOAD));
        
        //set default dialect and default schema
	
        //jpaProperties.put(PersistanceContextEnum.DEFAULT_SCHEMA.getKey(), databaseOrconsumerName);
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT,MySQL5Dialect.class.getName());
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		        log.info("retailDataStoreEM instance created");
        return entityManagerFactoryBean;
    }
    

}
