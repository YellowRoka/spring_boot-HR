package hu.webuni.hr.roka.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Employer;

public class EmployeeDto{

	private long id;
	@NotNull(message = "Set a name")
	private String name;
	@NotNull(message = "Set a grade")
	private Grade grade;
	@Min(value = 1, message = "must be greater than 0" )
	private int payment;
	@PastOrPresent(message = "future value not acceptable")//@Past
	private LocalDateTime firstDate;
	
	public EmployeeDto() {}
	
	public EmployeeDto(long id, String name, Grade grade, int payment, LocalDateTime firstDate) {
		super();
		this.id = id;
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
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
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
	
}
