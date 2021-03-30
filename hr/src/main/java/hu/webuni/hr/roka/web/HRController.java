package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class HRController {
	
	private Map<Long, EmployeeDto> employers = new HashMap<>();
	
	{
		LocalDateTime date_1 = LocalDateTime.of(2005, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_2 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_3 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_4 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		
		employers.put(0L, (new EmployeeDto(0L, "Ferenc", Grade.ceo,    20000, date_1)));
		employers.put(1L, (new EmployeeDto(1L, "Adam",   Grade.senior, 15000, date_2)));
		employers.put(2L, (new EmployeeDto(2L, "Erik",   Grade.medior, 10000, date_3)));
		employers.put(3L, (new EmployeeDto(3L, "Dave",   Grade.junior, 5000,  date_4)));
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employers.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
		EmployeeDto employer = employers.get(id);
		
		if (employer != null) {
			return ResponseEntity.ok(employer);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public EmployeeDto createEmployer(@RequestBody EmployeeDto newEmployer) {
		newEmployer.setFirstDate(LocalDateTime.now());
		
		employers.put(newEmployer.getId(), newEmployer);
		
		return newEmployer;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployer(@PathVariable long id ,@RequestBody EmployeeDto employerDto ){
		
		if(!employers.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		
		employerDto.setId(id);
		employers.put(id, employerDto);
		
		return ResponseEntity.ok(employerDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployer(@PathVariable long id){
		employers.remove(id);
	}
	
	@GetMapping("payment")
	public Stream<Entry<Long,EmployeeDto>> getEmployerByPayment(@RequestBody EmployeeDto employerDto) {
		return	employers.entrySet().stream().filter(emp -> emp.getValue().getPayment() > employerDto.getPayment());
	}
}
