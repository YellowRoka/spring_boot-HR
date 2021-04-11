package hu.webuni.hr.roka.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.model.Employer;

@Service
public class SalaryService{
	
	private EmployeeService employeeService;
	private Employer employer;
	
	public SalaryService(EmployeeService employeeService) {
		//super();
		this.employeeService = employeeService;

	}

	public int getNewSalary(int newSalaryPercent) {
		return (int)((1+newSalaryPercent)*
				(employeeService.getPayRaisePercent(employer)));
	}
	
}
