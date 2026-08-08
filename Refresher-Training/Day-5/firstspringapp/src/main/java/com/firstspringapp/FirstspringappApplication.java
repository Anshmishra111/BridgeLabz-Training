package com.firstspringapp;

import com.firstspringapp.component.EmployeeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstspringappApplication {

	private static final Logger logger = LoggerFactory.getLogger(FirstspringappApplication.class);

	public static void main(String[] args) {

		logger.info("Starting Spring Boot Application");

		ApplicationContext context = SpringApplication.run(
				FirstspringappApplication.class,
				args);

		logger.info("Spring Application Started Successfully");

		EmployeeBean employeeBean = context.getBean(EmployeeBean.class);

		employeeBean.setId("EMP001");
		employeeBean.setName("Ansh");

		employeeBean.getDepartmentBean()
				.setDepartment("Software Development");

		logger.debug("Employee Bean Created");

		employeeBean.showEmployeeDetails();

		logger.info("Employee details displayed successfully");
	}
}