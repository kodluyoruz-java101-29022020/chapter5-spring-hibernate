package com.company.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.project.annotation.HibernateTransactional;

@Aspect
@Component
public class HibernateTransactionAspect {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Around("@annotation(hibernateTransactional)")
	public Object execute(ProceedingJoinPoint proceedingJoinPoint, HibernateTransactional hibernateTransactional) throws Throwable {
		
		if(!hibernateTransactional.active()) {
			Object result = proceedingJoinPoint.proceed();
			return result;
		}
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		
		Object result = proceedingJoinPoint.proceed();
		
		session.merge(result);
		
		session.getTransaction().commit();
		
		return result;
	}
	
}
