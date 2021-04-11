package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;

public class CompanyService {
	
	private Map<Long, Company> companies = new HashMap<>();
		
	{
		Map<Long, Employer> employers_1 = new HashMap<>();
		Map<Long, Employer> employers_2 = new HashMap<>();
		
		LocalDateTime date_1 = LocalDateTime.of(2005, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_2 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_3 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		LocalDateTime date_4 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		
		employers_1.put(0L, (new Employer(0L, "Ferenc", Grade.ceo,    20000, date_1)));
		employers_1.put(1L, (new Employer(1L, "Adam",   Grade.senior, 10000, date_2)));
		employers_2.put(2L, (new Employer(2L, "Erik",   Grade.medior, 5000,  date_3)));
		employers_2.put(3L, (new Employer(3L, "Dave",   Grade.ceo,    10000, date_4)));

		companies.put(0L,new Company(123,"PC Kft","Budapest",employers_1));
		companies.put(1L,new Company(456,"NET Kft","Budapest",employers_2));
	}


	public List<Company> getAll( Boolean fullOn){
		if(fullOn==null)fullOn = false;
		if(fullOn == true)
			return new ArrayList<>(companies.values());
		else {
			Map<Long, Company> limitedCompaniesList = new HashMap<>();
			for(Map.Entry<Long, Company> entry : companies.entrySet()) {
				Company tmpCopy = new Company();
				tmpCopy.setId      ( entry.getValue().getId() ); 
				tmpCopy.setName    ( entry.getValue().getName() );
				tmpCopy.setLocation( entry.getValue().getLocation() );
				limitedCompaniesList.put(entry.getKey(), tmpCopy);
			}				
			return  new ArrayList<>(limitedCompaniesList.values());
		}	
	}
	
	public Company getCompanyById( long id, Boolean fullOn) {
		Company comp = new Company();
		if(fullOn == true)
			comp = companies.get(id);
		else {
			comp.setId(companies.get(id).getId());
			comp.setLocation(companies.get(id).getLocation());
			comp.setName(companies.get(id).getName());
			comp.setEmplyores(null);
		}
		return comp;
	}

	public Company newCompany( Company newComp) {
		companies.put(newComp.getId(), newComp); 
		return newComp; //visszajelzés
	}
	
	public void deleteCompany( long id) {
		companies.remove(id);
	}
	
	public Company modifyCompany( long id,  Company comp) {
		companies.containsKey(id);
		comp.setId(comp.getId());
		companies.put(id, comp);
		return companies.get(id);
	}
	
	public Company companyAddEmployer( long id,  Employer newEmp){
		Map<Long, Employer> tmpEmpList = companies.get(id).getEmplyores();
		tmpEmpList.put(newEmp.getId(), newEmp);
		return companies.get(id);
	}
	
	public Company companyRemoveEmployer( long compId, long empId){
		Map<Long, Employer> tmpEmpList = companies.get(compId).getEmplyores();
		tmpEmpList.remove(empId);
		return null;
	}
	
	public Company companyChangeEmployers( long compId,  List<Employer> newEmpList ){
		Map<Long, Employer> empList = companies.get(compId).getEmplyores();
		
		Map<Long, Employer> converter = new HashMap<>();
		
		Long idx = 0L;
		for (Employer it : newEmpList) {
			converter.put(idx++, it);
		}
		empList.clear();
		empList.putAll(converter);
		
		return companies.get(compId);
	}
}
