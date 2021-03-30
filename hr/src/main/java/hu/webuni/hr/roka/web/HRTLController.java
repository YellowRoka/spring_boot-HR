package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;

@Controller
public class HRTLController {
	
	private List<EmployeeDto> allEmployers = new ArrayList<>();
	
	{
		LocalDateTime date = LocalDateTime.now();
		allEmployers.add(new EmployeeDto(0L, "Ferenc", Grade.ceo, 20000, date));
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/emps")
	public String listEmployeers(Map<String, Object> model) {
		model.put("emps", allEmployers);
		model.put("newEmployer", new EmployeeDto());
		return "emps";
	}
	
	@PostMapping("/emps")
	public String addEmployer(EmployeeDto employer) {
		employer.setFirstDate(LocalDateTime.now());
		allEmployers.add(employer);
		return "redirect:emps";
	}
	
}
