package hu.webuni.hr.roka.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.model.Vacation;

public class EmployeeDto{

	private long id;
	
	@NotEmpty(message = "Set a name")
	private String name;
	
	@NotNull(message = "Set a grade")
	private Position grade;
	
	@Min(value = 1, message = "must be greater than 0" )
	private int payment;
	
	@PastOrPresent(message = "future value not acceptable")//@Past
	private LocalDateTime firstDate;
	
	private CompanyDto company;
	
	private Vacation vacation;

	public EmployeeDto() {}
	
	public EmployeeDto(String name, int payment, LocalDateTime firstDate) {
		super();
		this.name = name;
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
	public void setGrade(Position position) {
		this.grade = position;
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
	public CompanyDto getCompany() {
		return company;
	}
	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	
	public Vacation getVacation() {
		return vacation;
	}

	public void setVacation(Vacation vacation) {
		this.vacation = vacation;
	}
}
