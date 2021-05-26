package hu.webuni.hr.roka.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import hu.webuni.hr.roka.Type;

@NamedEntityGraph(name = "Company.full", attributeNodes = @NamedAttributeNode("employers"))
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

	public Company(String name, String location) {
		super();
		this.name      = name;
		this.location  = location;
	}
	
	public Company(String name, String location , List<Employer> employers) {
		super();
		this.name      = name;
		this.location  = location;
		this.employers = employers;
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

	public List<Employer> getEmployers() {
		return this.employers;
	}
	
	public void addEmployer(Employer employer) {
		employer.setCompany(this);
		this.employers.add(employer);
	}
	
	public void setEmployers(List<Employer> employers) {
		
		if(this.employers == null) {
			this.employers = employers;
		}
	}
	
	
}
