package hu.webuni.hr.roka.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.mapper.CompanyMapper;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.service.CompanyService;
import hu.webuni.hr.roka.service.EmployeeService;


@RestController
@RequestMapping("api/companies")
public class CompanyController {
	
	//@Autowired
	CompanyService companyService = new CompanyService();
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployerMapper employerMapper;
	
	@GetMapping
	public List<CompanyDto> getAll(@RequestParam(value="fullOn", required = false) Boolean fullOn){
		return companyMapper.companiesToDtos(companyService.getAll(fullOn));
	}
	
	@GetMapping("/{id}")
	public CompanyDto getCompanyById(@PathVariable long id, @RequestParam(value="fullOn", required=false) Boolean fullOn) {
		
		Company comp = companyService.getCompanyById(id, fullOn);
		if(comp ==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return companyMapper.companieToDto(comp);
	}
	
	@PostMapping
	public CompanyDto newCompany(@RequestBody CompanyDto newComp) {
	Company	createdComp = companyMapper.dtoToCompany(newComp);
		companyService.newCompany(createdComp); 
		return companyMapper.companieToDto(companyService.getCompanyById(createdComp.getId(),true)); //visszajelzés
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.deleteCompany(id);
	}
	
	@PutMapping("/{id}")
	public CompanyDto modifyCompany(@PathVariable long id, @RequestBody CompanyDto comp) {
		
		Company modComp = companyMapper.dtoToCompany(comp);
		Company selectedComp = companyService.getCompanyById(id, true);
		if(selectedComp == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		companyService.modifyCompany(id, modComp);
		return companyMapper.companieToDto(companyService.getCompanyById(id, true));
	}
	
	@PostMapping("/{id}")
	public CompanyDto companyAddEmployer(@PathVariable long id, @RequestBody EmployeeDto newEmp){
		Map<Long, Employer> tmpEmpList = companyService.getCompanyById(id, true).getEmplyores();
		tmpEmpList.put(id, employerMapper.dtoToEmployer(newEmp));
		return companyMapper.companieToDto(companyService.getCompanyById(id, true));
	}
	
	@DeleteMapping
	public CompanyDto companyRemoveEmployer(@RequestParam long compId,@RequestParam long empId){
		Map<Long, Employer> tmpEmpList = companyService.getCompanyById(compId, true).getEmplyores();
		tmpEmpList.remove(empId);
		return companyMapper.companieToDto(companyService.getCompanyById(compId, true));
	}
	
	@PutMapping
	public CompanyDto companyChangeEmployers(@RequestParam long compId, @RequestBody List<EmployeeDto> newEmpList ){
		Map<Long, Employer> empList = companyService.getCompanyById(compId, true).getEmplyores();
		
		Map<Long, Employer> converter = new HashMap<>();
		
		Long idx = 0L;
		for (Employer it : employerMapper.dtosToEmployers(newEmpList)) {
			converter.put(idx++, it);
		}
		empList.clear();
		empList.putAll(converter);
		
		return companyMapper.companieToDto(companyService.getCompanyById(compId, true));
	}

}
