package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.EmployeeRepository;

@Service
public class DefaultEmployeeService extends EmployerServiceAbsctract {

	public DefaultEmployeeService(EmployeeRepository employeeRepository) {
		super(employeeRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getPayRaisePercent(Employer employer) {
		//return (int)(employer.getPayment()*1.05);
		return (int)(0.05*100);
	}
	
}
