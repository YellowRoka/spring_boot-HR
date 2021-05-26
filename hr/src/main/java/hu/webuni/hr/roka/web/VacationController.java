package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.roka.VacationStateEnum;
import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.dto.VacationDto;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.mapper.VacationMapper;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Vacation;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.VacationRepository;
import hu.webuni.hr.roka.service.VacationService;

@RestController
@RequestMapping("api/vacation")
public class VacationController {

	@Autowired
	VacationService vacationService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployerMapper employerMapper;
	
	@Autowired
	VacationMapper vacationMapper;
	
	
	@PostMapping
	public VacationDto setVacation(@RequestBody Vacation vac) {
		Vacation vacation = vacationService.vacationSave(vac);
		return vacationMapper.vacationToDto(vacation);
	}
	
	@GetMapping
	public VacationDto getVacationById(@Param(value = "id") long id) {
		Vacation vacation = 
				vacationService.getVacation(id)
								.orElseThrow(()->
								new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return vacationMapper.vacationToDto(vacation);
	}
	
	@GetMapping("/all")
	public List<VacationDto> getAllVacation() {
		List<Vacation> vacations = vacationService.getAllVacation();
		
		return vacationMapper.vacationsToDtos(vacations);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVacation(@PathVariable long id) {
		VacationStateEnum vacationState = 
				vacationService.getVacation(id).get().getVacationState();
		
		if( (vacationState != VacationStateEnum.ALLOWED)||
			(vacationState != VacationStateEnum.DENIED) ){
			vacationService.vacationDelete(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PutMapping
	public VacationDto modifyVacationForEmloyer(@Param(value = "id") long id, @RequestBody Vacation vac) {
		Vacation vacation = vacationService.vacationModify(id, vac);
		return vacationMapper.vacationToDto(vacation);
	}
	
	@PostMapping("/boss")
	public VacationDto modifyVacationStateForEmloyerByBoss(@Param(value = "bossId") long bossId, @Param(value = "id") long id, @RequestBody Vacation vac) {
		Vacation vacation = vacationService.vacationModifyByBoss(bossId, id, vac);
		return vacationMapper.vacationToDto(vacation);
	}
	
	@PutMapping("/emp")
	public VacationDto vacationStateForEmloyer(@Param(value = "id") long id, @RequestBody Vacation vac) {
		
		VacationStateEnum vacationState = 
				vacationService.getVacation(id).get().getVacationState();
		Vacation vacation;
		
		if( (vacationState != VacationStateEnum.ALLOWED)||
		    (vacationState != VacationStateEnum.DENIED) ){
			 vacation = vacationService.vacationModify(id, vac);
			 return vacationMapper.vacationToDto(vacation);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/vac")
	public List<VacationDto> searchBetweenDate(@Param(value = "start")LocalDateTime firstDate, @Param(value = "end")LocalDateTime lastDate){
		List<Vacation> vacs = vacationService.searchBetweenDate(firstDate, lastDate);
		return vacationMapper.vacationsToDtos(vacs);
	}
	
	@GetMapping("/all2")
	public Page<VacationDto> getAll2(Pageable pageable) {
	 Page<Vacation> all = vacationService.getAll2(pageable);
	 return all.map(vacationMapper::vacationToDto);
	}
	
	@GetMapping("/example")
	public List<VacationDto> findVacationsByExample(@RequestBody Vacation example){
		List<Vacation> vacations = vacationService.searchWithExample(example);
		return vacationMapper.vacationsToDtos(vacations);
	}

}
