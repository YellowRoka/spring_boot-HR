package hu.webuni.hr.roka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;
import hu.webuni.hr.roka.dto.EmployeeDto;

@Entity
public class Position {

	@Id
	@GeneratedValue
	private long   id;
	private Grade  posName;
	private Req    req;
	private int    minimalPayment;
	

	@OneToOne
	private Employer employer;
	
	public Position() {};
	
	public Position(Grade posName, Req req, int minimalPayment) {
		super();
		this.posName = posName;
		this.req = req;
		this.minimalPayment = minimalPayment;
	}

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
	
	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		//if(this.employer == null) {
			this.employer = employer;
		//}
		//employer.setGrade(this);
	}
	
}
