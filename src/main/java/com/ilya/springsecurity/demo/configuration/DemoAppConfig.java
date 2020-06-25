package com.ilya.springsecurity.demo.configuration;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ilya.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	//set up  variable to hold the properties
	@Autowired
	private Environment env;
	
	//set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	//define bean for view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	//define a bean for our security datasource (with java code)
	@Bean
	public DataSource securityDataSource() {
		
		//create a connection pull
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		//set JDBC driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		//log the connection props
		logger.info(">>> jdbc.url="+env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user="+env.getProperty("jdbc.user"));
		logger.info(">>> jdbc.user="+env.getProperty("jdbc.password")); //for learning 
		//set up the database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		//set connection pool props
		securityDataSource
		.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource
		.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource
		.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		return securityDataSource;
	}
	
	//need a helper method
	//read environment property and convert to int
	private int getIntProperty(String propName) {
		return Integer.parseInt(env.getProperty(propName));
	}	
}
