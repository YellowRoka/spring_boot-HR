package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Employer;

public interface EmployeeRepository extends JpaRepository<Employer, Long>,JpaSpecificationExecutor<Employer> {

	//@EntityGraph(attributePaths = {"company, company.employres"})
	//@Query("SELECT e FROM Employer e")
	List<Employer> findAll();
	
	List<Employer> findByGrade(Grade grade);
	
	//List<Employer> findByNameIsContaining(String partString);
	
	List<Employer> findByNameIgnoreCaseStartsWith(String partString);
	
	List<Employer> findAllByFirstDateBetween(LocalDateTime firstDate, LocalDateTime lastDate);
}
