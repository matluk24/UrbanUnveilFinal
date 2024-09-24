package it.unicam.cs.ids.UrbanUnveil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.unicam.cs.ids.UrbanUnveil.api.Controller.AuthenticationController;
import it.unicam.cs.ids.UrbanUnveil.api.Controller.UserController;
import it.unicam.cs.ids.UrbanUnveil.api.models.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AuthenticationTests {

	@Autowired
	private AuthenticationController authC;
	
	@Autowired
	private UserController userC;
	
	@Test
	@Order(value = 1)
	public void registrazioneTest() {
		User u = new User("Mattia", "Luciani", "mattia@boh.it", "MNBHDGE", "1234", null);
		
		u =(User) authC.sighin(u).getBody();
		
		Assertions.assertEquals(u, userC.get(Integer.toUnsignedLong(1)).getBody());
		
	}
	
	@Test
	@Order(value = 2)
	public void BTest() {
		
		Assertions.assertEquals(authC.login("mattia@boh.it", "1234").getBody(), userC.get(Integer.toUnsignedLong(1)).getBody());
	}
}
