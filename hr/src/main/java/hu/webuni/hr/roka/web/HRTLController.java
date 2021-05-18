package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Position;

@Controller
public class HRTLController {
	
	private List<EmployeeDto> allEmployers = new ArrayList<>();
	
	{
		LocalDateTime date = LocalDateTime.now();
		allEmployers.add(new EmployeeDto("Ferenc", new Position(Grade.junior,Req.egyetem,500), 20000, date));
		allEmployers.add(new EmployeeDto("Géza",new Position(Grade.senior,Req.egyetem,500), 1000, date));
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
	
	private static int helperID = 0; //Nem tudtam szebben megoldani sajnos :\
	
	@RequestMapping(value = "/set")
	public String setEmployer(@RequestParam(value = "id") int id,Map<String, Object> model) {
		
		helperID = id;
		List<EmployeeDto> emp =  allEmployers.stream().filter(it -> it.getId() == id).collect(Collectors.toList());
		
		model.put("selectedEmployer", emp.get(0));
		return "set";
	}
	
	@PostMapping("/set")
	public String setEmployer(EmployeeDto employer) {

		allEmployers.get(helperID).setName(employer.getName());
		allEmployers.get(helperID).setGrade(employer.getGrade());
		allEmployers.get(helperID).setPayment(employer.getPayment());
		//allEmployers.get(helperID).setFirstDate(employer.getFirstDate());
		
		return "redirect:emps";
	}
	
	@RequestMapping(value = "/del")
	public String deleteEmployer(@RequestParam(value = "id")int id) {
		List<EmployeeDto> emp =  allEmployers.stream().filter(it -> it.getId() == id).collect(Collectors.toList());
		allEmployers.remove(emp.get(0));
		return "redirect:emps";
	}
	
}
