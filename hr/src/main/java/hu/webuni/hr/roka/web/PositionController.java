package hu.webuni.hr.roka.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.model.Company;
import hu.webuni.hr.roka.model.Position;
import hu.webuni.hr.roka.service.PositionService;

@RestController
@RequestMapping("api/position")
public class PositionController {

	@Autowired
	PositionService positionService;
	
	@PutMapping
	public List<Position> modifyPosition(int rise, @RequestParam Grade grade){

		return positionService.setRiseForPosByGrade(grade, rise);
	}
	
	@PutMapping("/C")
	public Company modifyPositionByCompany(@RequestParam Long compID, @RequestParam int rise, @RequestParam Grade grade){

		return positionService.setRiseForPosByCompId(compID,grade, rise);
	}
}
