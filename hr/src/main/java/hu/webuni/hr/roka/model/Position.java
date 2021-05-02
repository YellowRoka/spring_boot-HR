package hu.webuni.hr.roka.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.Req;

@Entity
public class Position {

	@Id
	@GeneratedValue
	private long   id;
	private Grade  posName;
	private Req    req;
	private int    minimalPayment;
	
	//@OneToMany(mappedBy = "grade")
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
/*	
	public Employer getEmployer() {
		return employer;
	}
*/
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public void addEmployer(Employer employer) {
		if(this.employer == null) {
			this.employer = employer;
		}
		employer.setGrade(this);
	}
	
}
