package hu.webuni.hr.roka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.roka.service.EmployeeService;
import hu.webuni.hr.roka.service.InitDbService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{
	//TODO: Több helyen megjelöltem //TODO: jelöléssel ahol végképp elakadtam
	//      még nagyon sok időt kéne ezekbe a fukciókba bele ölni, de nem szeretnék 
	//      még jobban lemearadni, így félkész, agyon kommentezett és TODO-val tűzdelt kódot vagyok
	//      kénytelen feltölteni, amiert nagyon nagyon mély elnézést kérek!
	
	@Autowired
	private InitDbService initDbService;
	//@Autowired
	//private EmployeeService employeeService;
/*	
	LocalDateTime date1 = LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 40);
	LocalDateTime date2 = LocalDateTime.of(2017, Month.JULY, 29, 19, 30, 40);
	LocalDateTime date3 = LocalDateTime.of(2010, Month.JULY, 29, 19, 30, 40);

	
	Employer employer1 =
			new Employer(0,"Béla",Grade.junior,1000,date1);

	Employer employer2 =
			new Employer(1,"Géza",Grade.senior,2000,date2);
	
	Employer employer3 =
			new Employer(3,"Zsolt",Grade.ceo,3000,date3);
*/		
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

//korábbi megoldott hiba:	
//Első meghívás esetén hibát dob mivel több service is definiálva van és nincs kijelölve default. 
//--> megoldva: megnevezés eggyezési hiba volt
	@Override
	public void run(String... args) throws Exception {
		
		initDbService.clearDB();
		initDbService.insertTestData();
	/*	
		System.out.println(
				employeeService.getPayRaisePercent(employer1));
		System.out.println(
				employeeService.getPayRaisePercent(employer2));
		System.out.println(
				employeeService.getPayRaisePercent(employer3));
	*/
	}

}
