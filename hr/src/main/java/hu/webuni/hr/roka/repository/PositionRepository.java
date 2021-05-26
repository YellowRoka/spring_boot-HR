package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	public List<Position> findByPosName(Grade posName);
}
