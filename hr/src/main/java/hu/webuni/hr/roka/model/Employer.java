package hu.webuni.hr.roka.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employer")
public class Employer {

	@Id
	@GeneratedValue
	private long   id;
	private String name;
	
	@OneToOne
	private Position      grade;	
	private int           payment;
	private LocalDateTime firstDate;
	
	@OneToOne
	private Vacation vacation;
	
	@ManyToOne
	private Company company;
	
	public Employer() {}
	
	public Employer(String name, int payment, LocalDateTime firstDate) {
		super();
		this.name = name;
		this.payment = payment;
		this.firstDate = firstDate;
	}

	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getGrade() {
		return this.grade;
	}
	public void setGrade(Position grade) {
		this.grade = grade;
	}
	public int getPayment() {
		return this.payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public LocalDateTime getFirstDate() {
		return this.firstDate;
	}
	public void setFirstDate(LocalDateTime firstDate) {
		this.firstDate = firstDate;
	}

	public Company getCompany() {
		return this.company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public Vacation getVacation() {
		return this.vacation;
	}
	
	public void setVacation(Vacation vacation) {
		this.vacation = vacation;	
	}
	
}
