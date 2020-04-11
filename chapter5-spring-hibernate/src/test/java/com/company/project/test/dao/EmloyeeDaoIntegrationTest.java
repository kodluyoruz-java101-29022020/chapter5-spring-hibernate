package com.company.project.test.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.company.project.ApplicationConfig;
import com.company.project.dao.EmployeeDAO;
import com.company.project.dao.entity.Employee;
import com.company.project.test.app.config.TestApplicationConfig;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@TestPropertySource({ "classpath:application.properties" })
public class EmloyeeDaoIntegrationTest {

	@Autowired
	private EmployeeDAO employeeDao;
	
	@Test
	public void insertEmployee() {
		
		Long maxEmpNo = employeeDao.findMaxId();
		
		Employee employee = new Employee();
		employee.setEmpNo(maxEmpNo + 1);
		employee.setName("Bahar");
		employee.setLastName("Umut");
		employee.setGender("M");
		employee.setBirthDate(new Date());
		employee.setHireDate(new Date());
		employee = employeeDao.save(employee);
		
		Employee emp = employeeDao.findById(employee.getEmpNo());
		Assert.assertTrue(emp!= null && emp.getEmpNo().equals(employee.getEmpNo()));
	}
}
