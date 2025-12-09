package com.example.addressbook;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AddressBookCountTest {

	
	@Test
	public void givenCityOrState_ShouldReturnCount() throws Exception {
	    AddressBookDBService db = new AddressBookDBService();
	    int byCity = db.countByCity("City");
	    int byState = db.countByState("State");
	    assertTrue(byCity >= 0);
	    assertTrue(byState >= 0);
	}

}
