package hu.webuni.hr.roka.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
	
	List<EmployeeDto> employersToDtos(List<Employer> employers);

	EmployeeDto employerToDto(Employer employer);

	Employer dtoToEmployer(@Valid EmployeeDto newEmployer);

	List<Employer> dtosToEmployers(List<EmployeeDto> newEmpList);
	
	@Mapping(target = "employers", ignore = true)
	CompanyDto companyToDto(Company company);
	
}
