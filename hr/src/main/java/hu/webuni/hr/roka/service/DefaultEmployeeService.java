package hu.webuni.hr.roka.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.model.Employer;

@Service
public class DefaultEmployeeService implements EmployeeService {

	public int getPayRaisePercent(Employer employer) {
		//return (int)(employer.getPayment()*1.05);
		return (int)(0.05*100);
	}
	
}
