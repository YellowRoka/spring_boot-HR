package hu.webuni.hr.roka.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.roka.model.AverageSalaryByPosition;
import hu.webuni.hr.roka.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>/*, PagingAndSortingRepository<Company, Long> ez nem kell, mert a JpaRepository eleve ebből örököl*/ {

	//@Query("SELECT * FROM company") //ilyet nem kell írni, by default van findAll(Pageable) a JpaRepository-ban
	//List<Company> pagebleFindAll(Pageable pageable);
	
	
	
	@Query("SELECT c FROM Company c WHERE SIZE(c.employers) > :count")
	List<Company> findAllCompanyWhereCountByEmplyoresGreaterThanEqual(int count);

	
	//@Query("select c, count(e.id) from company c left join employee e on c.id = e.company.id where e.lastname = :lastname group by c.id")
	//Page<Object[]> findAll(@Param("lastname") String lastname, Pageable pageable);
	
	@Query("SELECT c FROM Company c JOIN c.employers e WHERE e.payment > :payment")
	List<Company> findAllWhereEmployerPaymentIsBigger(int payment);

	@Query("SELECT e.grade.posName AS position, avg(e.payment) AS averageSalary "
			+ "FROM Company c "
			+ "JOIN c.employers e "
			+ "WHERE c.id=:companyID "
			+ "GROUP BY e.grade.posName "
			+ "ORDER BY avg(e.payment) DESC")
	List<AverageSalaryByPosition> groupEmployerByAveragePayment(long companyID);
	
	
	
	//@Query("SELECT DISTINCT c from Company c LEFT JOIN FETCH c.employers")
	//@EntityGraph("Company.full")
	@EntityGraph(attributePaths = {"employers", "employers.grade"})
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployers();

	@EntityGraph(attributePaths = {"employers", "employers.grade"})
	@Query("SELECT c FROM Company c")
	public Page<Company> findAllWithEmployers(Pageable pageable);

	
}

