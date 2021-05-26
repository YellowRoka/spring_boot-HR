package hu.webuni.hr.roka.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.PositionRepository;
import hu.webuni.hr.roka.service.CompanyService;
import hu.webuni.hr.roka.service.EmployeeService;
import hu.webuni.hr.roka.service.PositionService;


@SpringBootTest//(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class companyControllerIT {
	
	//private static final String BASE_URI = "/api/companies";
	
	//@Autowired
	//WebTestClient WebTestClient;
	
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	PositionService positionService;

	@BeforeEach
	public void init() {
		employeeRepository.deleteAll();
		positionRepository.deleteAll();
		companyRepository.deleteAll();	
		
		testCreateTables();
	}
	

	void testCreateTables() {// throws Exception{
		Position posJun = new Position(Grade.junior,Req.egyetem,500);
		Position posMed = new Position(Grade.medior,Req.egyetem,500);
		Position posSen = new Position(Grade.senior,Req.egyetem,500);
		Position posCeo = new Position(Grade.ceo,   Req.egyetem,500);
		
		positionRepository.save(posJun);
		positionRepository.save(posMed);
		positionRepository.save(posSen);
		positionRepository.save(posCeo);
		
		
		LocalDateTime date1 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp1 = new Employer("Adam", 1000, date1);
		emp1.setGrade(posJun);
		
		LocalDateTime date2 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp2 = new Employer( "Béla", 1000, date2);
		emp2.setGrade(posJun);
		
		LocalDateTime date3 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp3 = new Employer("Csaba", 1000, date3);
		emp3.setGrade(posJun);
		
		LocalDateTime date4 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp4 = new Employer("Eva", 5000, date4);
		emp4.setGrade(posMed);
		
		LocalDateTime date5 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp5 = new Employer("Géza", 5000, date5);
		emp5.setGrade(posMed);
		
		LocalDateTime date6 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp6 = new Employer("Ferenc", 7000, date6);
		emp6.setGrade(posSen);
		
		LocalDateTime date7 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp7 = new Employer("Károly", 7000, date7);
		emp7.setGrade(posSen);
		
		LocalDateTime date8 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp8 = new Employer("István", 10000, date8);
		emp8.setGrade(posCeo);
		
		LocalDateTime date9 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp9 = new Employer("László", 10000, date9);
		emp9.setGrade(posCeo);
		
		LocalDateTime date10 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp10 = new Employer("Zsolt", 10000, date10);
		emp10.setGrade(posCeo);
		
		
		
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
	}

	@Test
	void testCompanyAddEmployer() throws Exception{
		
		Position      posJun = new Position(Grade.junior,Req.egyetem,500);
		LocalDateTime date1  = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer      newEmp = new Employer("AAA", 1000, date1);
		
		List<Company> comps = companyRepository.findAll();
		List<Employer> emps = employeeRepository.findAll();
		List<Position> pos  = positionRepository.findAll();
		
		newEmp.setGrade(pos.get(0));
		newEmp.setCompany(comps.get(0));
		employeeRepository.save(newEmp);
		
		Optional<Company> compsAft = companyRepository.findById(comps.get(0).getId());
		List<Employer> empsAft =  employeeRepository.findAll();
		
		assertThat(newEmp.getCompany().getId()).isIn(compsAft.get().getId());
		//assertThat(newEmp).isIn(empsAft);
	}
	
	@Test
	void testCompanyRemoveEmployer() throws Exception{
		
		List<Employer> emps = employeeRepository.findAll();
		List<Company> comps = companyRepository.findAll();

		Optional<Company> compsAft = companyRepository.findById(comps.get(0).getId());

		long compId = compsAft.get().getId();
		long empId  = emps.get(0).getId();
		
		for( Employer emp : emps) {
			if( (compId == emp.getCompany().getId()) &&
				(emp.getId()==empId)) {
				emp.setCompany(null);
			}
		}
		
		employeeRepository.saveAll(emps);
		List<Employer> empsAft =  employeeRepository.findAll();
		
		assertThat(empsAft.get(0).getCompany()).isNull();

	}
	
	@Test
	void testCompanyChangeEmployers() throws Exception{

		List<Employer> emps = employeeRepository.findAll();
		List<Company> comps = companyRepository.findAll();

		Optional<Company> compsAft = companyRepository.findById(comps.get(0).getId());

		long compId = compsAft.get().getId();
		long empId  = emps.get(0).getId();
		
		for( Employer emp : emps) {
			if( (compId == emp.getCompany().getId()) &&
				(emp.getId()==empId)) {
				emp.setCompany(null);
			}
		}
		
		employeeRepository.saveAll(emps);
		
		
		LocalDateTime date1 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp1 = new Employer("FFF", 1000, date1);
		emp1.setCompany(comps.get(0));
		
		LocalDateTime date2 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp2 = new Employer( "GGG", 1000, date2);
		emp2.setCompany(comps.get(0));
		
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		
		List<Employer> empsAft = employeeRepository.findAll();
		
		assertThat(empsAft.get(10).getCompany().getId()).isEqualTo(comps.get(0).getId());
		assertThat(empsAft.get(11).getCompany().getId()).isEqualTo(comps.get(0).getId());
	}
}
