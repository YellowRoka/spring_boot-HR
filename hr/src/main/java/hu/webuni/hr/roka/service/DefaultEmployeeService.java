package hu.webuni.hr.roka.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.Employer;

@Service
public class DefaultEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employer employer) {
		return (int)(employer.getPayment()*1.05);
	}
	
}
