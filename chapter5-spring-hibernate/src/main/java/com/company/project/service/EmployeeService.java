package com.company.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.project.annotation.HibernateTransactional;
import com.company.project.annotation.Logger;
import com.company.project.annotation.enums.LogTimeUnit;
import com.company.project.dao.EmployeeDAO;
import com.company.project.dao.entity.Employee;
import com.company.project.dao.model.EmployeeProfile;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	
	public Long findMaxId() {
		
		return this.employeeDAO.findMaxId();
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public List<Employee> findAll() {
		
		return this.employeeDAO.findAll();
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public Employee findById(Long empNo) {
		
		return this.employeeDAO.findById(empNo);
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public Employee save(Employee employee) {
		
		return this.employeeDAO.save(employee);
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public Employee update(Employee employee) {
		
		return this.employeeDAO.update(employee);
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public void deleteById(Long empNo) {
		
		this.employeeDAO.delete(empNo);
	}
	
	public void delete(Employee employee) {
		
		this.employeeDAO.delete(employee);
	}
	
	public void delete(Long empNo) {
		
		this.employeeDAO.delete(empNo);
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public EmployeeProfile loadEmployeeProfile(Long empNo) {
		
		return this.employeeDAO.loadEmployeeProfile(empNo);
	}
	
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	@HibernateTransactional(active = true)
	public Employee saveWithAnnotation(Employee employee) {
		
		Employee employee1 = this.employeeDAO.saveWithoutTransaction(employee);
		return employee1;
	}
}
