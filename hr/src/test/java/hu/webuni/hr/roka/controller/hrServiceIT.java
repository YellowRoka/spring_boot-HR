package hu.webuni.hr.roka.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import hu.webuni.hr.roka.Grade;
import hu.webuni.hr.roka.dto.EmployeeDto;
import hu.webuni.hr.roka.model.Employer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class hrServiceIT {

	private static final String BASE_URI = "/api/employees";
	
	@Autowired
	WebTestClient WebTestClient;
	

	@Test
	//PUT
	void testThatCreatedEmployerIsListed() throws Exception {
		List<EmployeeDto> empBefore = getAllEmp();
		
		LocalDateTime date = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
		EmployeeDto newEmployer = new EmployeeDto(10,"Géza",Grade.ceo,30000,date);
		
		createEmp(newEmployer);
		
		List<EmployeeDto> employersAfter = getAllEmp();
		
		//valid input true positive
		assertThat(employersAfter.subList(0, empBefore.size()))
			.usingRecursiveFieldByFieldElementComparator()
			.containsExactlyElementsOf(empBefore);
		
		//valid input true positive
		assertThat(employersAfter.get(employersAfter.size()-1))
			.usingRecursiveComparison()
			.isEqualTo(newEmployer);
		
		//invalid input false positive
		assertThat(employersAfter.size())
			.isGreaterThan(empBefore.size());
		
		//invalid input false positive
		assertThat(employersAfter)
			.usingFieldByFieldElementComparator()
			.isNotEqualTo(empBefore);
	}
	
	@Test
	//POST
	void testThatEmployerisModified() throws Exception{
		List<EmployeeDto> empBefore = getAllEmp();
		
		int position = 1;
		
		EmployeeDto oldEmp = empBefore.get(position);
		EmployeeDto modifEmp = empBefore.get(position);
		modifEmp.setName("András");
		modifEmp.setGrade(Grade.ceo);
		modifEmp.setPayment(7777);
		
		
		modifyEmp(position, modifEmp);
		
		List<EmployeeDto> employersAfter = getAllEmp();
		
		//valid input true positive
		assertThat(employersAfter.get(position))
		.usingRecursiveComparison()
		.isEqualTo(modifEmp);
		
		//invalid input false positive
		assertThat(employersAfter.get(position))
		.isNotEqualTo(oldEmp);
		
	}
	private void modifyEmp(int position, EmployeeDto Emp) {
		WebTestClient
			.put()
			.uri(BASE_URI+"/"+Integer.toString(position))
			.bodyValue(Emp)
			.exchange()
			.expectStatus()
			.isOk();
		
	}
	
	private void createEmp(EmployeeDto newEmp) {
		WebTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmp)
			.exchange()
			.expectStatus()
			.isOk();
		
	}
	
	private List<EmployeeDto> getAllEmp() {
		List<EmployeeDto> responseList = 
				WebTestClient
					.get()
					.uri(BASE_URI)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBodyList(EmployeeDto.class)
					.returnResult().getResponseBody();
		
		
		Collections.sort(responseList,(a1,a2)->Long.compare(a1.getId(),a2.getId()));
		return responseList;
	}
}
