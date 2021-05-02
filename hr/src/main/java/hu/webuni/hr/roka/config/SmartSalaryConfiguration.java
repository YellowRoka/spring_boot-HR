package hu.webuni.hr.roka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.PositionRepository;
import hu.webuni.hr.roka.service.CompanyService;
import hu.webuni.hr.roka.service.EmployeeService;
import hu.webuni.hr.roka.service.EmployerServiceAbsctract;
import hu.webuni.hr.roka.service.PositionService;
import hu.webuni.hr.roka.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Bean
	EmployeeService employeeService() {
		return new SmartEmployeeService(employeeRepository);
	}
	
	@Bean
	CompanyService companyService() {
		return new CompanyService(companyRepository);
	}
	
	@Bean
	PositionService positionService() {
		return new PositionService(positionRepository,companyRepository);
	}

}
