package hu.webuni.hr.roka.dto;

import java.time.LocalDateTime;

import hu.webuni.hr.roka.VacationStateEnum;

public class VacationDto {

	private long id;
	private LocalDateTime vacationStart;
	private LocalDateTime vacatinoEnd;
	private VacationStateEnum vacationState;
	//private long employerId;
	
	private EmployeeDto employer;
	
	
	public VacationDto() {};
	/*
	public VacationDto(LocalDateTime vacationStart, LocalDateTime vacatinoEnd, VacationStateEnum vacationState,
			EmployeeDto employer) {
		super();
		this.vacationStart = vacationStart;
		this.vacatinoEnd = vacatinoEnd;
		this.vacationState = vacationState;
		this.employer = employer;
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDateTime getVacationStart() {
		return vacationStart;
	}

	public void setVacationStart(LocalDateTime vacationStart) {
		this.vacationStart = vacationStart;
	}

	public LocalDateTime getVacatinoEnd() {
		return vacatinoEnd;
	}

	public void setVacatinoEnd(LocalDateTime vacatinoEnd) {
		this.vacatinoEnd = vacatinoEnd;
	}

	public VacationStateEnum getVacationState() {
		return vacationState;
	}

	public void setVacationState(VacationStateEnum vacationState) {
		this.vacationState = vacationState;
	}

	public EmployeeDto getEmployer() {
		return employer;
	}

	public void setEmployer(EmployeeDto employer) {
		if(this.employer == null) {
			this.employer = employer;
		}
	}
	

}
