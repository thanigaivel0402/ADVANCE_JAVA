package com.example.addressbook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AddressBookAddContactTest {

	@Test
	public void givenNewContact_WhenAdded_ShouldPersist() throws Exception {
	    AddressBookDBService db = new AddressBookDBService();
	    ContactPerson p = new ContactPerson("Alice","Walker","Addr","City","State","11111","1234567890","alice@example.com");
	    boolean added = db.addContact(p);
	    assertTrue(added);
	    assertTrue(p.getId() > 0);
	}

	
}
