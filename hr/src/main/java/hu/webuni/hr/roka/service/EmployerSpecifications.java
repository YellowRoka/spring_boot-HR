package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Employer_;

public class EmployerSpecifications {

	public static Specification<Employer> hasId(long id) {
		return(root,cq,cb)->cb.equal(root.get(Employer_.id),id);
	}

	public static Specification<Employer> hasName(String name) {
		return(root,cq,cb)->cb.like(root.get(Employer_.name),name +"%");
	}

	public static Specification<Employer> hasCompany(String compName) {
		return(root,cq,cb)->cb.like(root.get(Employer_.company.getName()),compName +"%");
	}

	public static Specification<Employer> hasPayment(long payment) {
		return(root,cq,cb)->cb.equal(root.get(Employer_.payment),payment);
	}

	public static Specification<Employer> hasGrade(Grade gradeName) {
		return(root,cq,cb)->cb.equal(root.get(Employer_.grade),gradeName);
	}

	public static Specification<Employer> hasDate(LocalDateTime date) {
		LocalDateTime startOfD = LocalDateTime.of(date.toLocalDate(),LocalTime.of(0, 0));
		return(root,cq,cb)->cb.between(root.get(Employer_.firstDate),startOfD,startOfD.plusDays(1));
	
	}

}
