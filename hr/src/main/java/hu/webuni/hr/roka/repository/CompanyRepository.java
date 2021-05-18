package hu.webuni.hr.roka.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;

public interface CompanyRepository extends JpaRepository<Company, Long>, PagingAndSortingRepository<Company, Long> {

	//@Query("SELECT * FROM company")
	//List<Company> pagebleFindAll(Pageable pageable);
	
	
	//List<Company> findAllCompanyWhereCountByEmplyoresGreaterThanEqual(long count);

	//@Query("SELECT * c.emplyores"
	//		+ "FROM company c")
	//List<Company> countByEmplyoresGreaterThanEqual();
	
	//@Query("select c, count(e.id) from company c left join employee e on c.id = e.company.id where e.lastname = :lastname group by c.id")
	//Page<Object[]> findAll(@Param("lastname") String lastname, Pageable pageable);
	
	//List<Company> findAllWhereEmployerPaymentIsBigger(long payment);
	/*
	@Query("select c, e "
			+ "from company c, employer e "
			+ "where c.id = (companyID) left join employee e "
			+ "select avr from e"
			+ "group by e dsc")
	List<Employer> groupEmployerByAveragePayment(long companyID);
	 */
	
	
	
	//@Query("SELECT DISTINCT c from Company c LEFT JOIN FETCH c.employers")
	//@EntityGraph("Company.full")
	@EntityGraph(attributePaths = {"employers", "employers.grade"})
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployers();

	}

