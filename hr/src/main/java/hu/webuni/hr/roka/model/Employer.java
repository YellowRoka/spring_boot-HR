package hu.webuni.hr.roka.model;

import java.time.LocalDateTime;

import hu.webuni.hr.roka.Grade;

public class Employer {

	private long id;
	private String name;
	private Grade grade;
	private int payment;
	private LocalDateTime firstDate;
	
	public Employer() {}
	
	public Employer(long id, String name, Grade grade, int payment, LocalDateTime firstDate) {
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
