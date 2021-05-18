package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.mapper.EmployerMapper;
import hu.webuni.hr.roka.model.Employer;

	
public interface EmployeeService{

	public Employer employerSave(Employer employer);
	
	public Employer employerModify(long id, Employer employer);
	
	public void emloyerDelete(long id);
	
	public List<Employer> findAll();

	public Optional<Employer> findById(long id);
	
	public List<Employer> findByGrade(Grade grade);
	
	public List<Employer> findByPartName(String partString);
	
	public List<Employer> findBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate);
	
	public int getPayRaisePercent(Employer employer);

	public List<Employer> searchWithExample(EmployeeDto example);

}
