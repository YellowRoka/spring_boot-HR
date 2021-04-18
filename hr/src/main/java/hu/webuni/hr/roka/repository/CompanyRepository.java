package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
