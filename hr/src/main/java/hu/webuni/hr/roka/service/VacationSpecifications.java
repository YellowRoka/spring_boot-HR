package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.roka.VacationStateEnum;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Vacation;
import hu.webuni.hr.roka.model.Vacation_;

public class VacationSpecifications {

	public static Specification<Vacation> hasId(long id) {
		return(root,cq,cb)->cb.equal(root.get(Vacation_.id),id);
	}

	public static Specification<Vacation> hasStartDate(LocalDateTime startDate) {
		return(root,cq,cb)->cb.equal(root.get(Vacation_.vacationStart),startDate);
	}

	public static Specification<Vacation> hasEndDate(LocalDateTime endDate) {
		return(root,cq,cb)->cb.equal(root.get(Vacation_.vacationEnd),endDate);
	}

	public static Specification<Vacation> hasState(VacationStateEnum state) {
		return(root,cq,cb)->cb.equal(root.get(Vacation_.vacationState),state);
	}

	public static Specification<Vacation> hasEmployer(Employer employer) {
		return(root,cq,cb)->cb.equal(root.get(Vacation_.employer),employer);
	}

}
