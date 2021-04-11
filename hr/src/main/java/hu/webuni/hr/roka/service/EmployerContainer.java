package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Employer;


public class EmployerContainer {
	
	Map<Long, Employer> employers = new HashMap<>();
	
	{
		LocalDateTime date_1 = LocalDateTime.of(2005, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_2 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_3 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_4 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		
		employers.put(0L, (new Employer(0L, "Ferenc", Grade.ceo,    20000, date_1)));
		employers.put(1L, (new Employer(1L, "Adam",   Grade.senior, 15000, date_2)));
		employers.put(2L, (new Employer(2L, "Erik",   Grade.medior, 10000, date_3)));
		employers.put(3L, (new Employer(3L, "Dave",   Grade.junior, 5000,  date_4)));
	}
	
	
	public Employer employerSave(Employer employer) {
		employers.put(employer.getId(), employer);
		return employer;
	}
	
	public Employer employerModify(long id, Employer employer) {
		employer.setId(employer.getId());
		employers.put(id, employer);
		return employer;
	}
	
	public void emloyerDelete(long id) {
		employers.remove(id);
	}
	
	public List<Employer> findAll(){
		return new ArrayList<>(employers.values());
	}

	public Employer findById(long id) {
		return employers.get(id);
	}

	

}
