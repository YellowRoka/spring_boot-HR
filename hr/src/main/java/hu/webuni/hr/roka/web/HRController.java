package hu.webuni.hr.roka.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.service.EmployerContainer;


@RestController
@RequestMapping("/api/employees")
public class HRController {
	
	//@Autowired
	EmployerContainer employerService = new EmployerContainer();
	
	@Autowired
	EmployerMapper employerMapper;
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return employerMapper.employersToDtos(employerService.findAll());
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id){
		Employer employer = employerService.findById(id);
		
		if (employer != null) {
			return employerMapper.employerToDto(employer);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public EmployeeDto createEmployer(@RequestBody @Valid EmployeeDto newEmployer) {
		//newEmployer.setFirstDate(LocalDateTime.now());
		Employer employer = employerService.employerSave(employerMapper.dtoToEmployer(newEmployer));
		return employerMapper.employerToDto(employer);
	}
	
	@PutMapping("/{id}")
	public EmployeeDto modifyEmployer(@PathVariable long id ,@RequestBody @Valid EmployeeDto employerDto ){
		Employer employer = employerService.findById(id);
		
		if(null == employer) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Employer modderEmployer = employerMapper.dtoToEmployer(employerDto);
		Employer moddedEmployer = employerService.employerModify(id, modderEmployer);
		
		return employerMapper.employerToDto(moddedEmployer);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployer(@PathVariable long id){
		employerService.emloyerDelete(id);
	}
	
	@GetMapping("payment")
	public List<EmployeeDto> getEmployerByPayment(@RequestBody EmployeeDto employerDto) {
		List<Employer> employres = employerService.findAll();
		Employer employer = employerMapper.dtoToEmployer(employerDto);
		
		return	employerMapper.employersToDtos(employres
					.stream()
					.filter(emp -> emp.getPayment() > employer.getPayment()).collect(Collectors.toList()));
	}
	
	@GetMapping("search")
	public  List<EmployeeDto> getEmployerByPayment_2(@RequestParam String limit) {
		if(!limit.isEmpty()) {
			List<Employer> employres = employerService.findAll();
			return employerMapper.employersToDtos( employres
					.stream()
					.filter(emp -> emp.getPayment() > Integer.parseInt(limit))
					.collect(Collectors.toList()));
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
