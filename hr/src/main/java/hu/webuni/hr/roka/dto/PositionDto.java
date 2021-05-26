package hu.webuni.hr.roka.dto;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;

public class PositionDto {

	private long   id;
	private Grade  posName;
	private Req    req;
	private int    minimalPayment;
	
	private EmployeeDto employer;
	
	public PositionDto() {};
/*
	public PositionDto(Grade posName, Req req, int minimalPayment) {
		super();
		this.posName = posName;
		this.req = req;
		this.minimalPayment = minimalPayment;
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Grade getPosName() {
		return posName;
	}

	public void setPosName(Grade posName) {
		this.posName = posName;
	}

	public Req getReq() {
		return req;
	}

	public void setReq(Req req) {
		this.req = req;
	}

	public int getMinimalPayment() {
		return minimalPayment;
	}

	public void setMinimalPayment(int minimalPayment) {
		this.minimalPayment = minimalPayment;
	}
	
	public EmployeeDto getEmployer() {
		return employer;
	}

	public void setEmployer(EmployeeDto employer) {
		if(this.employer == null) {
			this.employer = employer;
		}
	}
/*
	public void addEmployer(EmployeeDto employer) {
		if(this.employer == null) {
			this.employer = employer;
		}
		employer.setGrade(this);
	}
	*/
}