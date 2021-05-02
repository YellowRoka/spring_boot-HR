package hu.webuni.hr.roka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import hu.webuni.hr.roka.Type;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private long     id;
	private String   name;
	private String   location;
	private Type     type;
	
	@OneToMany(mappedBy = "company")
	private List <Employer> employers;
	
	public Company(){}

	public Company(String name, String location, List<Employer> emplyores) {
		super();
		this.name      = name;
		this.location  = location;
		this.employers = emplyores;
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

	public List<Employer> getEmplyores() {
		return employers;
	}

	public void setEmplyores( List<Employer> emplyores) {
		this.employers = emplyores;
	}
	
	public void addEmployer(Employer employer) {
		if(this.employers == null) {
			this.employers = new ArrayList<>();
		}
		this.employers.add(employer);
		employer.setCompany(this);
	}
	
	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}
}
