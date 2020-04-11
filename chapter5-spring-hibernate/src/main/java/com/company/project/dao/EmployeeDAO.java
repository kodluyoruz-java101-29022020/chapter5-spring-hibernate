package com.company.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.project.dao.entity.Employee;
import com.company.project.dao.model.EmployeeProfile;

@Component
public class EmployeeDAO 
{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Long findMaxId() {
		
		Session session = sessionFactory.openSession();
		
		Query<Long> query = session.createQuery("select MAX(e.empNo) from Employee e", Long.class);
		
		return query.getSingleResult();
	}
	
	public List<Employee> findAll() {
		
		Session session = sessionFactory.openSession();
		
		Query<Employee> query = session.createQuery("select e from Employee e", Employee.class);
		
		return query.getResultList();
	}
	
	public Employee findById(Long empNo) {
		
		Session session = sessionFactory.openSession();
		
		Query<Employee> query = session.createQuery("select e from Employee e where empNo = :empNo", Employee.class);
		query.setParameter("empNo", empNo);
		
		return query.getSingleResult();
	}
	
	public EmployeeProfile loadEmployeeProfile(Long empNo) {
		
		Session session = sessionFactory.openSession();
		
		Query<EmployeeProfile> query = session.createQuery("select new com.company.project.dao.model.EmployeeProfile(e, dept.name) from Employee e left outer join e.departments dept where e.empNo = :empNo", EmployeeProfile.class);
		
		query.setParameter("empNo", empNo);
		
		return query.getSingleResult();
	}
	
	public Employee save(Employee employee) {
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		
		session.save(employee);
		
		session.getTransaction().commit();
		
		return employee;
	}
	
	
	public Employee saveWithoutTransaction(Employee employee) {
		
		Session session = sessionFactory.openSession();
		
		session.save(employee);
		
		return employee;
	}
	
	public Employee update(Employee employee) {
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		
		session.merge(employee);
		
		session.getTransaction().commit();
		
		return employee;
	}
	
	public void deleteById(Long empNo) {
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
	
		Query<Employee> query = session.createQuery("delete from Employee e where e.empNo = :empNo", Employee.class);
		
		query.executeUpdate();
		
		session.getTransaction().commit();
	}
	
	public void delete(Employee employee) {
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		
		session.delete(employee);
		
		session.getTransaction().commit();
	}
	
	public void delete(Long empNo) {
		
		Session session = sessionFactory.openSession();
		
		session.getTransaction().begin();
		
		session.delete(session.get(Employee.class, empNo));
		
		session.getTransaction().commit();
	}
}
