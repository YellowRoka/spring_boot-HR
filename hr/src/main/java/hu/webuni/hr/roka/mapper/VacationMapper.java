package hu.webuni.hr.roka.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.roka.dto.VacationDto;
import hu.webuni.hr.roka.model.Vacation;

@Mapper(componentModel = "spring")
public interface VacationMapper {
	
	VacationDto vacationToDto(Vacation vacation);
	
	Vacation dtoToVacation(VacationDto vacataionDto);

	List<VacationDto> vacationsToDtos(List<Vacation> vacations);

}
