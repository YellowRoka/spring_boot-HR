package hu.webuni.hr.roka.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.roka.Employer;

@Service
public class SmartEmployeeService implements EmployeeService
{
	
	@Value("${hr.employment.junior.limit}")
	private double juniorLimit;
	@Value("${hr.employment.junior.paymentrise}")
	private double juniorRise;
	
	@Value("${hr.employment.medior.limit}")
	private double mediorLimit;
	@Value("${hr.employment.medior.paymentrise}")
	private double mediorRise;
	
	@Value("${hr.employment.senior.limit}")
	private double seniorLimit;
	@Value("${hr.employment.senior.paymentrise}")
	private double seniorRise;
	
	@Value("${hr.employment.ceo.limit}")
	private double ceoLimit;
	@Value("${hr.employment.ceo.paymentrise}")
	private double ceoRise;

	@Override
	public int getPayRaisePercent(Employer employer) {
		int actYear       = LocalDateTime.now().getYear();
		int employeYear   = employer.getFirstDate().getYear();
		int actMounth     = LocalDateTime.now().getMonthValue();
		int employeMounth = employer.getFirstDate().getMonthValue();
		
		double calculatedTime = (actYear-employeYear) + ((double)(12-employeMounth+actMounth)/12.0);
		double risePercent    = 0;
		


		if     ( ceoLimit      <= calculatedTime)                          risePercent = ceoRise;
		else if( seniorLimit   <= calculatedTime && 10 > calculatedTime )  risePercent = seniorRise;
		else if( mediorLimit   <= calculatedTime && 5  > calculatedTime )  risePercent = mediorRise;
		else if( juniorLimit    > calculatedTime)                          risePercent = juniorRise;
		else                                                               risePercent = 0.0;
		
		//return (int)((employer.getPayment()*(1+risePercent)));
		return (int)(risePercent*100);
	}

}
