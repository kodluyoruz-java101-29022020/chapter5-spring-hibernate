package com.company.project;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.company.project.dao.HibernateConfig;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({
	"com.company.project.service",
	"com.company.project.dao",
	"com.company.project.console",
	"com.company.project.aspect"})
@Order(2)
@Import(HibernateConfig.class)
@PropertySource({ "classpath:application.properties" })
public class ApplicationConfig {

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
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		
	   return builder.build();
	}
	
}
