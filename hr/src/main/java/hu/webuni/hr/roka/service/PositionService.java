package hu.webuni.hr.roka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Employer;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.repository.CompanyRepository;
import hu.webuni.hr.roka.repository.PositionRepository;

public class PositionService {

	PositionRepository positionRepository;
	CompanyRepository companyRepository;

	public PositionService(PositionRepository positionRepository, CompanyRepository companyRepository) {
		super();
		this.positionRepository = positionRepository;
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public List<Position> setRiseForPosByGrade( Grade posName, int rise){
		
		List<Position> pos = positionRepository.findByPosName(posName);
		
		for(Position it : pos) {
			if(it.getPosName() == posName) {
				it.setMinimalPayment(it.getMinimalPayment()+rise);
			}
		}
		return positionRepository.saveAll(pos);
	} 
	
	@Transactional
	public Company setRiseForPosByCompId( long compID, Grade posName, int rise){
		/*
		Optional<Company> company = companyRepository.findById(compID);
		Company comp = company.get();
		List<Employer> emps = comp.getEmplyores();
		
		for(Employer it : emps) {
			if(it.getGrade().getPosName() == posName) {
				it.getGrade().setMinimalPayment(
						it.getGrade().getMinimalPayment()+rise);
			}
		}
		return companyRepository.save(comp);*/
		return null;
	} 
}
