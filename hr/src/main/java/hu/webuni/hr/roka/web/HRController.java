package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.annotations.Proxy;
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

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
@Proxy(lazy = false)
public class HRController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployerMapper employerMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	//TODO: most már ő sem hajlandó lefutni
	@GetMapping("/all")
	public List<EmployeeDto> getAll(){
		List<Employer> all = employeeService.findAll();
		return employerMapper.employersToDtos(all);
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id){
		Employer employer = employeeService.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
			return employerMapper.employerToDto(employer);
	}
	
	@PostMapping
	public EmployeeDto createEmployer(@RequestBody @Valid EmployeeDto newEmployer) {
		//newEmployer.setFirstDate(LocalDateTime.now());
		Employer employer = employeeService.employerSave(employerMapper.dtoToEmployer(newEmployer));
		return employerMapper.employerToDto(employer);
	}
	
	@PutMapping("/{id}")
	public EmployeeDto modifyEmployer(@PathVariable long id ,@RequestBody @Valid EmployeeDto employerDto ){
		Employer employer = employeeService.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

		Employer modderEmployer = employerMapper.dtoToEmployer(employerDto);
		
		try {
			Employer moddedEmployer = employeeService.employerModify(id, modderEmployer);
			return employerMapper.employerToDto(moddedEmployer);
		}
		catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}	
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployer(@PathVariable long id){
		employeeService.emloyerDelete(id);
	}
	
	@GetMapping("/payment")
	public List<EmployeeDto> getEmployerByPayment(@RequestBody EmployeeDto employerDto) {
		List<Employer> employres = employeeService.findAll();
		Employer employer = employerMapper.dtoToEmployer(employerDto);
		
		return	employerMapper.employersToDtos(employres
					.stream()
					.filter(emp -> emp.getPayment() > employer.getPayment()).collect(Collectors.toList()));
	}
	
	@GetMapping("/search")
	public  List<EmployeeDto> getEmployerByPayment_2(@RequestParam String limit) {
		if(!limit.isEmpty()) {
			List<Employer> employres = employeeService.findAll();
			return employerMapper.employersToDtos( employres
					.stream()
					.filter(emp -> emp.getPayment() > Integer.parseInt(limit))
					.collect(Collectors.toList()));
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/grade")
	public List<EmployeeDto> getEmployesByGrade(@RequestParam int grade){
		List<Employer> employers = employeeService.findByGrade(Grade.values()[grade]);
		return employerMapper.employersToDtos(employers);
	}
	
	@GetMapping("/partname")
	public List<EmployeeDto> findByString(@RequestParam String partString){
		List<Employer> employers = employeeService.findByPartName(partString);
		return employerMapper.employersToDtos(employers);
	}
	
	@GetMapping("/dates")
	public List<EmployeeDto> findBetweenDate(@RequestParam  String firstDate, @RequestParam  String lastDate){
		List<Employer> employers = employeeService.findBetweenDate(LocalDateTime.parse(firstDate),LocalDateTime.parse(lastDate));
		return employerMapper.employersToDtos(employers);
	}
	
	//TODO:ezt egyszer sikerült elindítani de valamit elrontottam valahol...azóta ez a funkció nem megy :( 
	@GetMapping("/example")
	public List<EmployeeDto> findEmployerByExample(@RequestBody EmployeeDto example){
		List<Employer> employers = employeeService.searchWithExample(example);
		return employerMapper.employersToDtos(employers);
	}

}
