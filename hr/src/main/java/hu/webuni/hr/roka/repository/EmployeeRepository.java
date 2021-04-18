package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Employer;

public interface EmployeeRepository extends JpaRepository<Employer, Long> {

	List<Employer> findByGrade(Grade grade);
	
	//List<Employer> findByNameIsContaining(String partString);
	
	List<Employer> findByNameIgnoreCaseStartsWith(String partString);
	
	List<Employer> findAllByFirstDateBetween(LocalDateTime firstDate, LocalDateTime lastDate);
}
