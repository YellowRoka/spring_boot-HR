package hu.webuni.hr.roka.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companiesToDtos(List<Company> company);

	
	CompanyDto companieToDto(Company company);
	
	@Mapping(target = "company.employers", ignore = true)
	EmployeeDto employeeToDto(Employer employer);

	Company dtoToCompany(@Valid CompanyDto newCompany);
	
	
	@Mapping(target = "employers", ignore = true)
	@Named("summary")
	CompanyDto companySummaryToDto(Company company);

	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companySummariesToDtos(List<Company> companies);

}

