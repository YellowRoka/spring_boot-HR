package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.dto.PositionDto;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;

@Controller
public class HRTLController {
	
	@Autowired
	EmployerMapper employerMapper;
	
	private List<Employer> allEmployers = new ArrayList<>();
	
	{
		LocalDateTime date = LocalDateTime.now();
		Employer emp1 = new Employer("Ferenc", 20000, date);
		emp1.setGrade( new Position(Grade.junior,Req.egyetem,500));
		allEmployers.add(emp1);
		Employer emp2 = new Employer("Géza", 1000, date);
		emp2.setGrade(new Position(Grade.senior,Req.egyetem,500));
		allEmployers.add(emp2);
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
		allEmployers.add(employerMapper.dtoToEmployer(employer));
		return "redirect:emps";
	}
	
	private static int helperID = 0; //Nem tudtam szebben megoldani sajnos :\
	
	@RequestMapping(value = "/set")
	public String setEmployer(@RequestParam(value = "id") int id,Map<String, Object> model) {
		
		helperID = id;
		List<Employer> emp =  allEmployers.stream().filter(it -> it.getId() == id).collect(Collectors.toList());
		
		model.put("selectedEmployer", emp.get(0));
		return "set";
	}
	
	@PostMapping("/set")
	public String setEmployer(EmployeeDto employer) {
		Employer emp = employerMapper.dtoToEmployer(employer);
		
		allEmployers.get(helperID).setName(emp.getName());
		allEmployers.get(helperID).setGrade(emp.getGrade());
		allEmployers.get(helperID).setPayment(emp.getPayment());
		//allEmployers.get(helperID).setFirstDate(employer.getFirstDate());
		
		return "redirect:emps";
	}
	
	@RequestMapping(value = "/del")
	public String deleteEmployer(@RequestParam(value = "id")int id) {
		List<Employer> emp =  allEmployers.stream().filter(it -> it.getId() == id).collect(Collectors.toList());
		allEmployers.remove(emp.get(0));
		return "redirect:emps";
	}
	
}
