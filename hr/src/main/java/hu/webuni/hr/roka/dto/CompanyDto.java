package hu.webuni.hr.roka.dto;

import java.util.List;
import java.util.Map;

import hu.webuni.hr.roka.Type;

public class CompanyDto {
	private long   id;
	private String name;
	private String location;
	private Type   type;
	private List<EmployeeDto> employers;
	
	public CompanyDto(){}
/*
	public CompanyDto(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}
	
	public CompanyDto(String name, String location, List<EmployeeDto> emplyores) {
		super();
		this.name = name;
		this.location = location;
		//this.employers = emplyores;
	}
*/
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public List<EmployeeDto> getEmployers() {
		return employers;
	}

	public void setEmployers( List<EmployeeDto> employers) {
		if(this.employers == null) {
			this.employers = employers;
		}
		//emplyores.forEach(it->it.setCompany(this));
		//}
		
	}
	
	
	public void addEmployers(EmployeeDto employer) {
		employer.setCompany(this);
		this.employers.add(employer);
	}
	
	
}
