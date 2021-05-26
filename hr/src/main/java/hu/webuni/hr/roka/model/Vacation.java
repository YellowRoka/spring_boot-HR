package hu.webuni.hr.roka.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.webuni.hr.roka.VacationStateEnum;

@Entity
@Table(name = "vacation")
public class Vacation {

	@Id
	@GeneratedValue
	private long   id;

	private LocalDateTime vacationStart;
	private LocalDateTime vacationEnd;
	private VacationStateEnum vacationState;
	
	@OneToOne
	private Employer employer;

	public Vacation() {};
	
	public Vacation(LocalDateTime vacationStart, LocalDateTime vacatinoEnd, VacationStateEnum vacationState) {
		super();
		this.vacationStart = vacationStart;
		this.vacationEnd = vacatinoEnd;
		this.vacationState = vacationState;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDateTime getVacationStart() {
		return this.vacationStart;
	}

	public void setVacationStart(LocalDateTime vacationStart) {
		this.vacationStart = vacationStart;
	}

	public LocalDateTime getVacatinoEnd() {
		return this.vacationEnd;
	}

	public void setVacatinoEnd(LocalDateTime vacatinoEnd) {
		this.vacationEnd = vacatinoEnd;
	}

	public VacationStateEnum getVacationState() {
		return this.vacationState;
	}

	public void setVacationState(VacationStateEnum vacationState) {
		this.vacationState = vacationState;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		//if(this.employer == null) {
			this.employer = employer;
		//}
		//this.employer.setVacation(this);
	}
/*
	public void addEmployer(Employer employer) {
		if(this.employer == null) {
			this.employer = employer;
		}
		employer.setVacation(this);
	}
*/
}
