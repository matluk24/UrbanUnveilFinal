package it.unicam.cs.ids.UrbanUnveil;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.unicam.cs.ids.UrbanUnveil.api.Controller.OSMController;
import it.unicam.cs.ids.UrbanUnveil.api.Controller.POIController;
import it.unicam.cs.ids.UrbanUnveil.api.Controller.UserController;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.RoleEnum;
import it.unicam.cs.ids.UrbanUnveil.api.models.POI;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;

@SpringBootTest
public class POITests {
	
	@Autowired
	private POIController c;
	
	@Autowired
	private OSMController osmc;
	
	@Autowired
	private UserController userc;

	@Test
	public void addPOITest() {
		
		POI p=null;
		User u = new User("Mattia", "Luciani", "mattia@boh.it", "MNBHDGE", "1234", null);
		
		System.out.println(u);
		
		userc.add(u);
		System.out.println(userc.getAll().getBody());
		
		try {
			 p= new POI(osmc.search("New York").getBody(), userc.get(Integer.toUnsignedLong(1)).getBody(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(c.add(p));
		
	}
}
