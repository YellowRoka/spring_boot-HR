package hu.webuni.hr.roka.web;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
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
import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;


@RestController
@RequestMapping("api/companies")
public class CompanyController {
	
	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	
	{
		Map<Long, EmployeeDto> employers_1 = new HashMap<>();
		Map<Long, EmployeeDto> employers_2 = new HashMap<>();
		
		LocalDateTime date_1 = LocalDateTime.of(2005, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_2 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_3 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_4 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		
		employers_1.put(0L, (new EmployeeDto(0L, "Ferenc", Grade.ceo,    20000, date_1)));
		employers_1.put(1L, (new EmployeeDto(1L, "Adam",   Grade.senior, 10000, date_2)));
		employers_2.put(2L, (new EmployeeDto(2L, "Erik",   Grade.medior, 5000,  date_3)));
		employers_2.put(3L, (new EmployeeDto(3L, "Dave",   Grade.ceo,    10000, date_4)));

		companies.put(0L,new CompanyDto(123,"PC Kft","Budapest",employers_1));
		companies.put(1L,new CompanyDto(456,"NET Kft","Budapest",employers_2));
	}
	
	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(value="fullOn", required = false) Boolean fullOn){
		if(fullOn==null)fullOn = false;
		if(fullOn == true)
			return new ArrayList<>(companies.values());
		else {
			Map<Long, CompanyDto> limitedCompaniesList = new HashMap<>();
			for(Map.Entry<Long, CompanyDto> entry : companies.entrySet()) {
				CompanyDto tmpCopy = new CompanyDto();
				tmpCopy.setId      ( entry.getValue().getId() ); 
				tmpCopy.setName    ( entry.getValue().getName() );
				tmpCopy.setLocation( entry.getValue().getLocation() );
				limitedCompaniesList.put(entry.getKey(), tmpCopy);
			}				
			return  new ArrayList<>(limitedCompaniesList.values());
		}	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id, @RequestParam(value="fullOn", required=false) Boolean fullOn) {
		CompanyDto comp = new CompanyDto();
		
		if(fullOn==null)fullOn = false;
		if(fullOn == true)
			comp = companies.get(id);
		else {
			comp.setId(companies.get(id).getId());
			comp.setLocation(companies.get(id).getLocation());
			comp.setName(companies.get(id).getName());
			comp.setEmplyores(null);
		}
			
		if(comp != null)
			return ResponseEntity.ok(comp);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public CompanyDto newCompany(@RequestBody CompanyDto newComp) {
		companies.put(newComp.getId(), newComp); 
		return newComp; //visszajelzés
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto comp) {
		if(companies.containsKey(id)) {
			comp.setId(comp.getId());
			companies.put(id, comp);
			return ResponseEntity.ok(comp);
			}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}")
	public CompanyDto companyAddEmployer(@PathVariable long id, @RequestBody EmployeeDto newEmp){
		Map<Long, EmployeeDto> tmpEmpList = companies.get(id).getEmplyores();
		tmpEmpList.put(newEmp.getId(), newEmp);
		return companies.get(id);
	}
	
	@DeleteMapping
	public CompanyDto companyRemoveEmployer(@RequestParam long compId,@RequestParam long empId){
		Map<Long, EmployeeDto> tmpEmpList = companies.get(compId).getEmplyores();
		tmpEmpList.remove(empId);
		return null;
	}
	
	@PutMapping
	public CompanyDto companyChangeEmployers(@RequestParam long compId, @RequestBody List<EmployeeDto> newEmpList ){
		Map<Long, EmployeeDto> empList = companies.get(compId).getEmplyores();
		
		Map<Long, EmployeeDto> converter = new HashMap<>();
		
		Long idx = 0L;
		for (EmployeeDto it : newEmpList) {
			converter.put(idx++, it);
		}
		empList.clear();
		empList.putAll(converter);
		
		return companies.get(compId);
	}
}
