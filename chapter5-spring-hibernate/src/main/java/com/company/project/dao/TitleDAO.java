package com.company.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.project.dao.entity.Title;

@Component
public class TitleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Title> findAll() {
		
		Session session = sessionFactory.openSession();
		
		Query<Title> query =  session.createQuery("SELECT t FROM Title t", Title.class);
		
		return query.getResultList();
	}
}
