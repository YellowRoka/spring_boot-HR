package hu.webuni.hr.roka.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;

import hu.webuni.hr.roka.dto.CompanyDto;
import hu.webuni.hr.roka.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companiesToDtos(List<Company> company);

	CompanyDto companieToDto(Company company);

	Company dtoToCompany(@Valid CompanyDto newCompany);

}

