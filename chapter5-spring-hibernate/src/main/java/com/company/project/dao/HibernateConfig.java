package com.company.project.dao;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Order(1)
@PropertySource({ "classpath:application.properties" })
public class HibernateConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		
		return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.company.project.dao.entity");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();
		factory.setJpaProperties(additionalProperties());
		return factory.getObject();
	}
	
	private final Properties additionalProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		return properties;
	}
	
}
