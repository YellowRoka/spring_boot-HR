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

	@Query("SELECT c FROM Company c WHERE SIZE(c.employers) > :count")
	public List<Company> findAllCompanyWhereCountByEmplyoresGreaterThanEqual(int count);

	@Query("SELECT c FROM Company c JOIN c.employers e WHERE e.payment > :payment")
	public List<Company> findAllWhereEmployerPaymentIsBigger(int payment);

	@Query("SELECT e.grade.posName AS position, avg(e.payment) AS averageSalary "
			+ "FROM Company c "
			+ "JOIN c.employers e "
			+ "WHERE c.id=:companyID "
			+ "GROUP BY e.grade.posName "
			+ "ORDER BY avg(e.payment) DESC")
	public List<AverageSalaryByPosition> groupEmployerByAveragePayment(long companyID);
	
	@EntityGraph(attributePaths = {"employers", "employers.grade"})
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployers();
	
	@EntityGraph(attributePaths = {"employers", "employers.grade"})
	@Query("SELECT c FROM Company c")
	public Page<Company> findAllWithEmployers(Pageable pageable);

	
	}
