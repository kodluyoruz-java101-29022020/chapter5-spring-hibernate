package com.company.project.test.app.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import com.company.project.dao.HibernateConfig;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({
	"com.company.project.service",
	"com.company.project.dao",
	"com.company.project.console",
	"com.company.project.aspect"})
@Order(2)
@Import(HibernateConfig.class)
public class TestApplicationConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	@Primary
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}	
}
