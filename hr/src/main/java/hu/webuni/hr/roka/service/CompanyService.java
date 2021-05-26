package hu.webuni.hr.roka.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.roka.model.AverageSalaryByPosition;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.CompanyRepository;

public class CompanyService {
	
	CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	public List<Company> getAll( Boolean fullOn){
		return fullOn ? companyRepository.findAllWithEmployers() : companyRepository.findAll();
	}
		
	public Company getCompanyById( long id, Boolean fullOn) {
		Company comp = companyRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(fullOn==null)fullOn = false;
		if(fullOn != true){
			comp.setId(comp.getId());
			comp.setLocation(comp.getLocation());
			comp.setName(comp.getName());
			comp.setEmployers(null);
		}
		return comp;
	}

	@Transactional
	public Company newCompany(Company newComp) {
		return companyRepository.save(newComp);
	}
	
	public void deleteCompany(long id) {
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
	public Company extendCompany( long id,  Company company) {
		company.setId(id);
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company companyAddEmployer( long id,  Employer newEmp){
		Company tmpComp = companyRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<Employer> tmpEmpList = tmpComp.getEmployers();
		tmpEmpList.add(newEmp);
		
		tmpComp.setEmployers(tmpEmpList);
		return companyRepository.save(tmpComp);
	}
	
	@Transactional
	public Company companyRemoveEmployer( long compId, long empId){
		Company tmpComp = companyRepository.findById(compId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		List<Employer> tmpEmpList = tmpComp.getEmployers();
		int idx = 0;
		for(Employer emp : tmpEmpList) {
			if(empId == emp.getId()) break;
			idx++;
			
		}
		tmpEmpList.remove(idx);
		tmpComp.setEmployers(tmpEmpList);
		
		return companyRepository.save(tmpComp);
	}
	
	@Transactional
	public Company companyChangeEmployers( long compId,  List<Employer> newEmpList ){
		Company tmpComp = companyRepository.findById(compId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		tmpComp.setEmployers(null);
		tmpComp.setEmployers(newEmpList);
		
		return companyRepository.save(tmpComp);
	}
	
	//TODO: az alábbi 4 NULL-os lekérdezést nem tudom összehozni sokadig nekifutásra sem :\
	//TODO: 1
	public List<Company> getCompaniesWithGivenHeadCnt(int headCount){
		return companyRepository.findAllCompanyWhereCountByEmplyoresGreaterThanEqual(headCount);
	}
	
	//TODO: 2
	public List<Company> getCompaniesWhereEmployerPaymentIsBigger(int payment){
		return companyRepository.findAllWhereEmployerPaymentIsBigger(payment);
	}
	
	//TODO:3
	public List<AverageSalaryByPosition> getEmployersByAVGPayment(long companyID){
		return companyRepository.groupEmployerByAveragePayment(companyID);
	}
	
	//TODO:4 nem lapoz
	public Page<Company> getAll2(Pageable pageable, boolean summaryOnly) {
		Page<Company> all;
		all= summaryOnly ? 
				companyRepository.findAll(pageable) : 
				companyRepository.findAllWithEmployers(pageable);
		return all;
	}

}
