package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.PositionRepository;

@Service
public class InitDbService {
	
	public InitDbService(CompanyRepository companyRepository, EmployeeRepository employeeRepository, PositionRepository positionRepository) {
		super();
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
		this.positionRepository = positionRepository;
	}

	CompanyRepository  companyRepository;
	EmployeeRepository employeeRepository;
	PositionRepository positionRepository;
	
	@Transactional
	public void clearDB() {
		positionRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}

	public List<Company> insertTestData() {
		
		Position posJun = new Position(Grade.junior,Req.egyetem,500);
		Position posMed = new Position(Grade.medior,Req.egyetem,500);
		Position posSen = new Position(Grade.senior,Req.egyetem,500);
		Position posCeo = new Position(Grade.ceo,   Req.egyetem,500);
		
		positionRepository.save(posJun);
		positionRepository.save(posMed);
		positionRepository.save(posSen);
		positionRepository.save(posCeo);
		
		
		LocalDateTime date1 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp1 = new Employer("Adam",posJun, 1000, date1);
		//employeeRepository.save(emp1);
		
		LocalDateTime date2 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp2 = new Employer( "Béla", posJun, 1000, date2);
		//employeeRepository.save(emp2);
		
		LocalDateTime date3 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp3 = new Employer("Dave", posJun, 1000, date3);
		//employeeRepository.save(emp3);
		
		LocalDateTime date4 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp4 = new Employer("Eva",posMed, 5000, date4);
		//employeeRepository.save(emp4);
		
		LocalDateTime date5 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp5 = new Employer("Géza",posMed, 5000, date5);
		//employeeRepository.save(emp5);
		
		LocalDateTime date6 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp6 = new Employer("Ferenc", posMed, 7000, date6);
		//employeeRepository.save(emp6);
		
		LocalDateTime date7 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp7 = new Employer("Károly", posSen, 7000, date7);
		//employeeRepository.save(emp7);
		
		LocalDateTime date8 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp8 = new Employer("István", posCeo, 10000, date8);
		//employeeRepository.save(emp8);
		
		LocalDateTime date9 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp9 = new Employer("László", posCeo, 10000, date9);
		//employeeRepository.save(emp9);
		
		LocalDateTime date10 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp10 = new Employer("Zsolt", posCeo, 10000, date10);
		//employeeRepository.save(emp10);
		
		
		
		Company comp1 = new Company("Net Bt.", "Budapest");
		companyRepository.save(comp1);

		Company comp2 = new Company("PC Kft.", "Budapest");
		companyRepository.save(comp2);
		
		Company comp3 = new Company("ISO Zrt.", "Budapest");
		companyRepository.save(comp3);
		
		emp1.setCompany(comp1);employeeRepository.save(emp1);
		emp2.setCompany(comp1);employeeRepository.save(emp2);
		emp8.setCompany(comp1);employeeRepository.save(emp8);
		
		emp3.setCompany(comp2);employeeRepository.save(emp3);
		emp4.setCompany(comp2);employeeRepository.save(emp4);
		emp6.setCompany(comp2);employeeRepository.save(emp6);
		emp9.setCompany(comp2);employeeRepository.save(emp9);
		
		emp5.setCompany(comp3);employeeRepository.save(emp5);
		emp7.setCompany(comp3);employeeRepository.save(emp7);
		emp10.setCompany(comp3);employeeRepository.save(emp10);
		
		comp1.setEmployers( Arrays.asList(emp1,emp2,emp8));
		comp2.setEmployers( Arrays.asList(emp3,emp4,emp6,emp9));
		comp3.setEmployers( Arrays.asList(emp5,emp7,emp10));
		
		companyRepository.save(comp1);
		companyRepository.save(comp2);
		companyRepository.save(comp3);
		
		return Arrays.asList(comp1, comp2, comp3);
	}

}
