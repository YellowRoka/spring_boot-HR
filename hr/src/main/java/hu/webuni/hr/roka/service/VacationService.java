package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.roka.VacationStateEnum;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Vacation;
import hu.webuni.hr.roka.repository.EmployeeRepository;
import hu.webuni.hr.roka.repository.VacationRepository;

//@Service
public class VacationService {
	
	VacationRepository vacationRepository;
	EmployeeRepository employeeRepository;

	public VacationService(VacationRepository vacationRepository,EmployeeRepository employeeRepository) {
		super();
		this.vacationRepository = vacationRepository;
		this.employeeRepository = employeeRepository;
	}
	
	//@Transactional
	public Optional<Vacation> getVacation(long id) {
		return vacationRepository.findById(id);
	}
	
	public List<Vacation> getAllVacation() {
		return vacationRepository.findAll();
	}
	
	@Transactional
	public Vacation vacationSave(Vacation vac) {
		return vacationRepository.save(vac);
	}
	
	@Transactional
	public void vacationDelete(long id) {
		vacationRepository.deleteById(id);
	}
	
	public List<Vacation> searchBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate){
		List<Vacation> vacs = vacationRepository.findAllByVacationStartBetween(firstDate,lastDate);
		return vacs;
	}
	
	@Transactional
	public Vacation vacationModify(long id, Vacation vac) {
		Vacation findVac = 
				vacationRepository
				.findById(id)
				.orElseThrow(()
						->new ResponseStatusException(HttpStatus.NOT_FOUND));
		try {
			 if(vac.getVacationStart() != null) {
				 findVac.setVacationStart(vac.getVacationStart());
			 }
			 if(vac.getVacatinoEnd() != null) {
				 findVac.setVacatinoEnd(vac.getVacatinoEnd());
			 }
			 if(vac.getVacationState() != null) {
				 findVac.setVacationState(vac.getVacationState());
			 }
			 if(vac.getEmployer() != null) {
				 findVac.setEmployer(vac.getEmployer());
			 }
			 
			 return vacationRepository.save(findVac);
		}
		catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Vacation vacationModifyByBoss(long bossId, long id, Vacation vac) {
		Employer boss = 
				employeeRepository
				.findById(bossId)
				.orElseThrow(()
						->new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Vacation findVac = 
				vacationRepository
				.findById(id)
				.orElseThrow(()
						->new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		int bossPosName   = boss.getGrade().getPosName().getValue();
		int workerPosName = vac.getEmployer().getGrade().getPosName().getValue();
		
		if( bossPosName<workerPosName) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		try {
			 if(vac.getVacationStart() != null) {
				 findVac.setVacationStart(vac.getVacationStart());
			 }
			 if(vac.getVacatinoEnd() != null) {
				 findVac.setVacatinoEnd(vac.getVacatinoEnd());
			 }
			 if(vac.getVacationState() != null) {
				 findVac.setVacationState(vac.getVacationState());
			 }
			 if(vac.getEmployer() != null) {
				 findVac.setEmployer(vac.getEmployer());
			 }
			 
			 return vacationRepository.save(findVac);
		}
		catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public List<Vacation> searchWithExample(Vacation example) {
		long id = example.getId();
		
		Employer          employer  = example.getEmployer();
		LocalDateTime     startDate = example.getVacationStart();
		LocalDateTime     endDate   = example.getVacatinoEnd();
		VacationStateEnum state     = example.getVacationState();
		
		Specification<Vacation> spec = Specification.where(null);
		
		if(id>0) {
			spec = spec.and(VacationSpecifications.hasId(id));
		}
		
		if(employer != null) {
			spec = spec.and(VacationSpecifications.hasEmployer(employer));
		}
		
		if(startDate != null) {
			spec = spec.and(VacationSpecifications.hasStartDate(startDate));
		}
		
		if(endDate != null) {
			spec = spec.and(VacationSpecifications.hasEndDate(endDate));
		}
		
		if(state != null) {
			spec = spec.and(VacationSpecifications.hasState(state));
		}
		
		List<Vacation> retList = vacationRepository.findAll(spec, Sort.by("id"));
		return retList;
	}

	public Page<Vacation> getAll2(Pageable pageable) {
		return vacationRepository.findAllWithEmployers(pageable);
	}


}
