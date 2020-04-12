package com.company.project.console;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.company.project.annotation.Logger;
import com.company.project.annotation.enums.LogTimeUnit;
import com.company.project.dao.entity.Employee;
import com.company.project.dao.entity.Title;
import com.company.project.dao.model.EmployeeProfile;
import com.company.project.service.EmployeeService;
import com.company.project.service.TitleService;

@Component
public class ConsoleApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TitleService titleService;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		//List<Title> titles = titleService.findAll();
		
		/*
		for(Title title : titles) {
			System.out.println(title);
		}
		*/
		
		// selectAllEmployees();
		
		// loadEmployeeProfile();
		
		insertEmployee();
		
		// updateEmployee();
		
		// deleteEmployee();
		
		// insertEmployeeWithAnnotation();
		
	}
	
	@Logger(timeUnit = LogTimeUnit.MILLISECOND)
	public void loadEmployeeProfile() {
		
		EmployeeProfile employeeProfile = this.employeeService.loadEmployeeProfile(10002L);
		
		System.out.println("Çalışan Profili");
		
		String fullName = employeeProfile.getEmployee().getName() + " " + employeeProfile.getEmployee().getLastName();
		System.out.println(employeeProfile.getEmployee().getEmpNo() + " - " + fullName);
		System.out.println("Departman ismi: " + employeeProfile.getDepartmentName());
	}
	
	private void selectAllEmployees() {
		
		List<Employee> employees = employeeService.findAll();
		System.out.println(employees.size());
	}
	
	private void insertEmployee() {
		
		Long maxEmpNo = employeeService.findMaxId();
		
		Employee employee = new Employee();
		employee.setEmpNo(maxEmpNo + 1);
		employee.setName("Bahar");
		employee.setLastName("Umut");
		employee.setGender("M");
		employee.setBirthDate(new Date());
		employee.setHireDate(new Date());
		
		employee = employeeService.save(employee);
		System.out.println(employee);
	}
	
	private void insertEmployeeWithAnnotation() {
		
		Long maxEmpNo = employeeService.findMaxId();
		
		Employee employee = new Employee();
		employee.setEmpNo(maxEmpNo + 1);
		employee.setName("Bahar");
		employee.setLastName("Umut");
		employee.setGender("M");
		employee.setBirthDate(new Date());
		employee.setHireDate(new Date());
		
		employee = employeeService.saveWithAnnotation(employee);
		System.out.println(employee);
	}
	
	private void updateEmployee() {
		
		Employee employee = employeeService.findById(10002L);
		employee.setName("Batuhan");
		employee.setLastName("Düzgün");
		employee.setGender("M");
		employee = employeeService.update(employee);
		System.out.println(employee);
	}
	
	private void deleteEmployee() {
		
		Long maxEmpNo = employeeService.findMaxId();
		employeeService.deleteById(maxEmpNo);
	}

}
