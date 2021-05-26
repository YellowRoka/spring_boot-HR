package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.roka.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation,Long>,JpaSpecificationExecutor<Vacation> {

	public List<Vacation> findAllByVacationStartBetween(LocalDateTime firstDate, LocalDateTime lastDate);
	
	public List<Vacation> findAllByVacationEndBetween(LocalDateTime firstDate, LocalDateTime lastDate);
	
	@EntityGraph(attributePaths = {"employer", "employer.vacation"})
	@Query("SELECT v FROM Vacation v")
	public Page<Vacation> findAllWithEmployers(Pageable pageable);
}
