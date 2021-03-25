package hu.webuni.hr.roka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.roka.service.DefaultEmployeeService;
import hu.webuni.hr.roka.service.EmployeeService;

@Configuration
@Profile("default")
public class DefaultSalaryConfiguration {
	
	@Bean
	EmployeeService employeeService(){
		return new DefaultEmployeeService();
	}

}
