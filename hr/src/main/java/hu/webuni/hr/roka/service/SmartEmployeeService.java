package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.Employer;

@Service
public class SmartEmployeeService implements EmployeeService
{

	@Override
	public int getPayRaisePercent(Employer employer) {
		int actTime = LocalDateTime.now().getYear();
		int employeTime = employer.getFirstDate().getYear();
		int calculatedTime = actTime-employeTime;
		double risePercent = 0;

		if     ( 10  <= calculatedTime)                         risePercent = 0.10;
		else if( 5   <= calculatedTime && 10 > calculatedTime ) risePercent = 0.05;
		else if( 2.5 <= calculatedTime && 5  > calculatedTime)  risePercent = 0.02;
		else if( 2    > calculatedTime)                         risePercent = 0.0;
		else                                                    risePercent = 0.0;
		
		return (int)((employer.getPayment()*(1+risePercent)));
	}

}
