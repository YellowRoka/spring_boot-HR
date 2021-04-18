package hu.webuni.hr.roka.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.service.DefaultEmployeeService;
import hu.webuni.hr.roka.service.EmployeeService;
import hu.webuni.hr.roka.service.EmployerServiceAbsctract;

@Configuration
@Profile("default")
public class DefaultSalaryConfiguration {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Bean
	EmployeeService employeeService(){
		return new DefaultEmployeeService(employeeRepository);
	}

}
