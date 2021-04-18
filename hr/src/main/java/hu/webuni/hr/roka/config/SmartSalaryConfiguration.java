package hu.webuni.hr.roka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.service.EmployeeService;
import hu.webuni.hr.roka.service.EmployerServiceAbsctract;
import hu.webuni.hr.roka.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Bean
	EmployeeService employeeService() {
		return new SmartEmployeeService(employeeRepository);
	}

}
