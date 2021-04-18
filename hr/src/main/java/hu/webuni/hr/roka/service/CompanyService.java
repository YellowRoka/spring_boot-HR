package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;

public class CompanyService {
	
	CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	public List<Company> getAll( Boolean fullOn){
		List<Company> companies = companyRepository.findAll();
		
		if(fullOn==null)fullOn = false;
		if(fullOn == true)
			return companies;
		else {
			List<Company> limitedCompaniesList = new ArrayList<>();
			
			for(Company entry : companies) {
				Company tmpCopy = new Company();
				tmpCopy.setId      ( entry.getId() ); 
				tmpCopy.setName    ( entry.getName() );
				tmpCopy.setLocation( entry.getLocation() );
				limitedCompaniesList.add(tmpCopy);
			}				
			return limitedCompaniesList;
		}	
	}
	
	public Company getCompanyById( long id, Boolean fullOn) {
		Company comp = companyRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(fullOn==null)fullOn = false;
		if(fullOn != true){
			comp.setId(comp.getId());
			comp.setLocation(comp.getLocation());
			comp.setName(comp.getName());
			comp.setEmplyores(null);
		}
		return comp;
	}

	@Transactional
	public Company newCompany( Company newComp) {
		return companyRepository.save(newComp);
	}
	
	public void deleteCompany( long id) {
		companyRepository.deleteById(id);
	}
	
	@Transactional
	public Company modifyCompany( long id,  Company company) {
		//Company compCheck = companyRepository.findById(id)
		//		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		company.setId(id);
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company companyAddEmployer( long id,  Employer newEmp){
		Company tmpComp = companyRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<Employer> tmpEmpList = tmpComp.getEmplyores();
		tmpEmpList.add(newEmp);
		
		tmpComp.setEmplyores(tmpEmpList);
		return companyRepository.save(tmpComp);
	}
	
	@Transactional
	public Company companyRemoveEmployer( long compId, long empId){
		Company tmpComp = companyRepository.findById(compId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<Employer> tmpEmpList = tmpComp.getEmplyores();
		int idx = 0;
		for(Employer emp : tmpEmpList) {
			if(empId == emp.getId()) break;
			idx++;
			
		}
		tmpEmpList.remove(idx);
		tmpComp.setEmplyores(tmpEmpList);
		
		return companyRepository.save(tmpComp);
	}
	
	@Transactional
	public Company companyChangeEmployers( long compId,  List<Employer> newEmpList ){
		Company tmpComp = companyRepository.findById(compId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		tmpComp.setEmplyores(null);
		tmpComp.setEmplyores(newEmpList);
		
		return companyRepository.save(tmpComp);
	}
}
