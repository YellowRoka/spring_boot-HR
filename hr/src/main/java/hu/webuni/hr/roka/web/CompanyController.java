package hu.webuni.hr.roka.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.service.CompanyService;
import hu.webuni.hr.roka.service.EmployeeService;


@RestController
@RequestMapping("api/companies")
public class CompanyController {

	@Autowired
	CompanyRepository companyRepository;
	
	
	@Autowired
	CompanyService companyService;// = new CompanyService(companyRepository);
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployerMapper employerMapper;
	
	//TODO: itt sikerült valamit addig addig csiszolgatnom még legalább egy 660 soror rekurziót kidobott
	//      ezután sikerült redukálni 440-re
	//      majd olyan jól sikerült a redukálás, hogy többet nem volt hajlandó listázni az alkalmazottakat
	//      annak ellenére h PGAdminban látszik a kapcsolat....bár én néhol sokallom az idegen kulcsok számát
	@GetMapping("/all")
	public List<CompanyDto> getAll(@RequestParam(value="fullOn", required = false) Boolean fullOn){
		return (fullOn == null || fullOn == false)?
				companyMapper.companiesToDtos(companyRepository.findAllWithEmployers()):
					companyMapper.companiesToDtos(companyService.getAll(fullOn));
		
	}
	
	@GetMapping("/all2")
	public Page<Company> getAll2(){
	 Page<Company> all = companyService.getAll2();
	 return all;
				
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
		return companyMapper.companieToDto(companyService.newCompany(createdComp)); 
		//return companyMapper.companieToDto(companyService.getCompanyById(createdComp.getId(),true)); //visszajelzés
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
		return companyMapper.companieToDto(companyService.modifyCompany(id, modComp));
		//return companyMapper.companieToDto(companyService.getCompanyById(id, true));
	}
	
	@PostMapping("/{id}")
	public CompanyDto companyAddEmployer(@PathVariable long id, @RequestBody EmployeeDto newEmp){
		List<Employer> tmpEmpList = companyService.getCompanyById(id, true).getEmployers();
		tmpEmpList.add(employerMapper.dtoToEmployer(newEmp));
		return companyMapper.companieToDto(companyService.getCompanyById(id, true));
	}
	
	@DeleteMapping
	public CompanyDto companyRemoveEmployer(@RequestParam long compId,@RequestParam long empId){
		//List<Employer> tmpEmpList = companyService.getCompanyById(compId, true).getEmplyores();
		//tmpEmpList.remove(empId);
		return companyMapper.companieToDto(companyService.companyRemoveEmployer(compId, empId));
		//return companyMapper.companieToDto(companyService.getCompanyById(compId, true));
	}
	
	@PutMapping
	public CompanyDto companyChangeEmployers(@RequestParam long compId, @RequestBody List<EmployeeDto> newEmpList ){
		Company empList =
				companyService.companyChangeEmployers(compId, employerMapper.dtosToEmployers(newEmpList));
		
		return companyMapper.companieToDto(empList);//companyMapper.companieToDto(companyService.getCompanyById(compId, true));
	}

	
	@GetMapping
	public List<CompanyDto> getCompaniesByGivenHeadCount(@RequestParam long headCount){
		return companyMapper.companiesToDtos(
				companyService.getCompaniesWithGivenHeadCnt(headCount));
	}
	
	@GetMapping("/payment")
	public List<CompanyDto> getCompaniesWhereEmployerPaymentIsBigger(@RequestParam long payLimit){
		return companyMapper.companiesToDtos(
				companyService.getCompaniesWhereEmployerPaymentIsBigger(payLimit));
	}
	
	@GetMapping("/avg")
	public List<EmployeeDto> getEmployersGroupByAVGPayment(@RequestParam long companyID){
		return employerMapper.employersToDtos(
				companyService.getEmployersByAVGPayment(companyID));
	}
}
