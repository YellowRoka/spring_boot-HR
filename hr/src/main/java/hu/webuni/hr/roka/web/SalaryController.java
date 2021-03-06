package hu.webuni.hr.roka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.service.EmployeeService;

@RestController
@RequestMapping("api/salary")
public class SalaryController {
	
	@Autowired
	EmployeeService employeeService;

	
	@PostMapping
	public int getEmployerPaymentRise(@RequestBody Employer employer ){
		return employeeService.getPayRaisePercent(employer);
	}
}
