package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.VacationStateEnum;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.model.Vacation;
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.PositionRepository;
import hu.webuni.hr.roka.repository.VacationRepository;

@Service
public class InitDbService {
	
	public InitDbService(CompanyRepository companyRepository, EmployeeRepository employeeRepository, PositionRepository positionRepository,VacationRepository vacationRepository) {
		super();
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
		this.positionRepository = positionRepository;
		this.vacationRepository = vacationRepository;
	}

	CompanyRepository  companyRepository;
	EmployeeRepository employeeRepository;
	PositionRepository positionRepository;
	VacationRepository vacationRepository;
	
	@Transactional
	public void clearDB() {
		positionRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		vacationRepository.deleteAll();
	}

	//@Transactional
	public List<Company> insertTestData() {
		Company comp1 = new Company("Net Bt.", "Budapest");
		Company comp2 = new Company("PC Kft.", "Budapest");
		Company comp3 = new Company("ISO Zrt.", "Budapest");
		
			companyRepository.save(comp1);
			companyRepository.save(comp2);
			companyRepository.save(comp3);
		
		
		Position posJun1 = new Position(Grade.junior,Req.egyetem,500);
		Position posJun2 = new Position(Grade.junior,Req.egyetem,500);
		Position posJun3 = new Position(Grade.junior,Req.egyetem,500);
		
		Position posMed1 = new Position(Grade.medior,Req.egyetem,500);
		Position posMed2 = new Position(Grade.medior,Req.egyetem,500);
		
		Position posSen1 = new Position(Grade.senior,Req.egyetem,500);
		Position posSen2 = new Position(Grade.senior,Req.egyetem,500);
		
		Position posCeo1 = new Position(Grade.ceo,   Req.egyetem,500);
		Position posCeo2 = new Position(Grade.ceo,   Req.egyetem,500);
		Position posCeo3 = new Position(Grade.ceo,   Req.egyetem,500);
		
			positionRepository.save(posJun1);
			positionRepository.save(posJun2);
			positionRepository.save(posJun3);
			positionRepository.save(posMed1);
			positionRepository.save(posMed2);
			positionRepository.save(posSen1);
			positionRepository.save(posSen2);
			positionRepository.save(posCeo1);
			positionRepository.save(posCeo2);
			positionRepository.save(posCeo3);
			
		LocalDateTime     dateStart1 = LocalDateTime.of(2021, Month.JUNE, 29, 00, 00, 00);
		LocalDateTime     dateEnd1   = LocalDateTime.of(2021, Month.JUNE, 30, 00, 00, 00);
		VacationStateEnum vacState1  = VacationStateEnum.WAITING;
		
		LocalDateTime     dateStart2 = LocalDateTime.of(2021, Month.JULY, 10, 00, 00, 00);
		LocalDateTime     dateEnd2   = LocalDateTime.of(2021, Month.JULY, 15, 00, 00, 00);
		VacationStateEnum vacState2  = VacationStateEnum.WAITING;
		
		LocalDateTime     dateStart3 = LocalDateTime.of(2021, Month.AUGUST, 01, 00, 00, 00);
		LocalDateTime     dateEnd3   = LocalDateTime.of(2021, Month.AUGUST, 15, 00, 00, 00);
		VacationStateEnum vacState3  = VacationStateEnum.WAITING;
		
		Vacation vac1 = new Vacation(dateStart1, dateEnd1, vacState1);
		Vacation vac2 = new Vacation(dateStart2, dateEnd2, vacState2);
		Vacation vac3 = new Vacation(dateStart3, dateEnd3, vacState3);
		
			vacationRepository.save(vac1);
			vacationRepository.save(vac2);
			vacationRepository.save(vac3);
		
		LocalDateTime date1 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp1 = new Employer("Adam", 1000, date1);
		
		LocalDateTime date2 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp2 = new Employer( "Béla", 1000, date2);
		
		LocalDateTime date3 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		Employer emp3 = new Employer("Csaba", 1000, date3);
		
		LocalDateTime date4 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp4 = new Employer("Eva", 5000, date4);
		
		LocalDateTime date5 = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
		Employer emp5 = new Employer("Géza", 5000, date5);
		
		LocalDateTime date6 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp6 = new Employer("Ferenc", 7000, date6);
		
		LocalDateTime date7 = LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40);
		Employer emp7 = new Employer("Károly", 7000, date7);
		
		LocalDateTime date8 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp8 = new Employer("István", 10000, date8);
		
		LocalDateTime date9 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp9 = new Employer("László", 10000, date9);
		
		LocalDateTime date10 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);
		Employer emp10 = new Employer("Zsolt", 10000, date10);
	
		emp1.setGrade(posJun1);
		emp2.setGrade(posJun2);
		emp3.setGrade(posJun3);
		emp4.setGrade(posMed1);
		emp5.setGrade(posMed2);
		emp6.setGrade(posSen1);
		emp7.setGrade(posSen2);
		emp8.setGrade(posCeo1);
		emp9.setGrade(posCeo2);
		emp10.setGrade(posCeo3);
		
		emp1.setCompany(comp1);
		emp2.setCompany(comp1);
		emp8.setCompany(comp1);
		
		emp3.setCompany(comp2);
		emp4.setCompany(comp2);
		emp6.setCompany(comp2);
		emp9.setCompany(comp2);
		
		emp5.setCompany(comp3);
		emp7.setCompany(comp3);
		emp10.setCompany(comp3);
		
		emp1.setVacation(vac1);
		emp2.setVacation(vac2);
		emp3.setVacation(vac3);
		
			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);
			employeeRepository.save(emp4);
			employeeRepository.save(emp5);
			employeeRepository.save(emp6);
			employeeRepository.save(emp7);
			employeeRepository.save(emp8);
			employeeRepository.save(emp9);
			employeeRepository.save(emp10);
		
		comp1.setEmployers( Arrays.asList(emp1,emp2,emp8));
		comp2.setEmployers( Arrays.asList(emp3,emp4,emp6,emp9));
		comp3.setEmployers( Arrays.asList(emp5,emp7,emp10));
		
			companyRepository.save(comp1);
			companyRepository.save(comp2);
			companyRepository.save(comp3);
		
/*		
		vac1.setEmployer(emp1);
		vac2.setEmployer(emp2);
		vac3.setEmployer(emp3);
		
		vacationRepository.save(vac1);
		vacationRepository.save(vac2);
		vacationRepository.save(vac3);
		
		posJun1.setEmployer(emp1);
		posJun2.setEmployer(emp2);
		posJun3.setEmployer(emp3);
		
		posMed1.setEmployer(emp4);
		posMed2.setEmployer(emp5);
		
		posSen1.setEmployer(emp6);
		posSen2.setEmployer(emp7);
		
		posCeo1.setEmployer(emp8);
		posCeo2.setEmployer(emp9);
		posCeo3.setEmployer(emp10);
		
			positionRepository.save(posJun1);
			positionRepository.save(posJun2);
			positionRepository.save(posJun3);
			positionRepository.save(posMed1);
			positionRepository.save(posMed2);
			positionRepository.save(posSen1);
			positionRepository.save(posSen2);
			positionRepository.save(posCeo1);
			positionRepository.save(posCeo2);
			positionRepository.save(posCeo3);
*/
		return Arrays.asList(comp1, comp2, comp3);
	}

}
