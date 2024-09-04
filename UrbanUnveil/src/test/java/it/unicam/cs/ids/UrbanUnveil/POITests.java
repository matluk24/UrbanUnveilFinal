package it.unicam.cs.ids.UrbanUnveil;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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
		try {
			 p= new POI(osmc.search("New York").getBody(), userc.get(Integer.toUnsignedLong(1)).getBody(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p= c.add(p).getBody();
		POI pt = c.getByID(Integer.toUnsignedLong(3)).getBody();
		
		Assertions.assertEquals(p, pt);
		
	}
	
	@Test
	public void getAllTestAndRemove() {
		List<POI> l = new LinkedList<POI>();
	
		User u = new User("Mattia", "Luciani", "mattia@boh.it", "MNBHDGE", "1234", RoleEnum.CONTRIBUTOR);
		
		userc.add(u);
		
		System.out.println(userc.getAll());		
		POI p=null;
		try {
			 p= new POI(osmc.search("Osimo").getBody(), userc.get(Integer.toUnsignedLong(1)).getBody(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(p);
		l.add(p);
		try {
			 p= new POI(osmc.search("Castelfidardo").getBody(), userc.get(Integer.toUnsignedLong(1)).getBody(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(p);
		l.add(p);
		
		Assertions.assertEquals(l, c.getAll().getBody());
		Assertions.assertEquals(c.remove(Integer.toUnsignedLong(1)).getStatusCode(), HttpStatus.OK);
		c.remove(Integer.toUnsignedLong(2));
		
	}
}
