package hu.webuni.hr.roka.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hu.webuni.hr.roka.Grade;

@Entity
public class Employer {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	//@ManyToOne
	@OneToOne
	private Position grade;
	
	private int payment;
	private LocalDateTime firstDate;
	
	@ManyToOne
	private Company company;
	
	public Employer() {}
	
	public Employer(/*long id,*/ String name, Position grade, int payment, LocalDateTime firstDate) {
		super();
		//this.id = id;
		this.name = name;
		this.grade = grade;
		this.payment = payment;
		this.firstDate = firstDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getGrade() {
		return grade;
	}
	public void setGrade(Position grade) {
		this.grade = grade;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public LocalDateTime getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(LocalDateTime firstDate) {
		this.firstDate = firstDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}
