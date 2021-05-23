package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.repository.EmployeeRepository;

@Service
public abstract class EmployerServiceAbsctract implements EmployeeService{
		
	EmployeeRepository employeeRepository;
	
	public EmployerServiceAbsctract(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	@Override
	public Employer employerSave(Employer employer) {
		return employeeRepository.save(employer);
	}
	
	@Transactional
	@Override
	public Employer employerModify(long id, Employer employer) {
		employer.setId(id);
		return employeeRepository.save(employer);
	}
	
	@Override
	public void emloyerDelete(long id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public List<Employer> findAll(){
		return employeeRepository.findAll();
	}
	
	@Override
	public Optional<Employer> findById(long id) {
		return employeeRepository.findById(id);
	}
	
	@Override
	public List<Employer> findByGrade(Grade grade){
		return employeeRepository.findByGrade(grade);
	}
	
	@Override
	public List<Employer> findByPartName(String partString){
		return employeeRepository.findByNameIgnoreCaseStartsWith(partString);
	}
	
	@Override
	public List<Employer> findBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate){
		return  employeeRepository.findAllByFirstDateBetween(firstDate, lastDate);
	}

	@Override
	public abstract int getPayRaisePercent(Employer employer);
	
	public List<Employer> searchWithExample(EmployeeDto example) {
		long id = example.getId();
		
		CompanyDto comp = example.getCompany();
		LocalDateTime date = example.getFirstDate();
		
		String name = example.getName();
		long payment = example.getPayment();
		
		Specification<Employer> spec = Specification.where(null);
		
		
		if(id>0) {
			spec = spec.and(EmployerSpecifications.hasId(id));
		}
		
		if (StringUtils.hasText(name)) {
			spec = spec.and(EmployerSpecifications.hasName(name));
		}
		
		if (comp != null) {
			String compName = example.getCompany().getName();
			spec = spec.and(EmployerSpecifications.hasCompany(compName));
		}
		
		if (payment>0) {
			spec = spec.and(EmployerSpecifications.hasPayment(payment));
		}
		
		if (example.getGrade() != null) {
			Grade gradeName = example.getGrade().getPosName();
			spec = spec.and(EmployerSpecifications.hasGrade(gradeName));
		}
		
		if (date != null) {
			spec = spec.and(EmployerSpecifications.hasDate(date));
		}
		
		List<Employer> retList = employeeRepository.findAll(spec, Sort.by("id"));
		return retList;
	}
	
}
