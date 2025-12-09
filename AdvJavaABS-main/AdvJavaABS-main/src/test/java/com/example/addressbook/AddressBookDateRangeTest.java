package com.example.addressbook;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class AddressBookDateRangeTest {

	@Test
	public void givenDateRange_WhenQueried_ShouldReturnExpectedContacts() throws Exception {
	    AddressBookDBService db = new AddressBookDBService();
	    LocalDateTime from = LocalDateTime.now().minusDays(7);
	    LocalDateTime to = LocalDateTime.now();
	    List<ContactPerson> list = db.getContactsInDateRange(from, to);
	    assertNotNull(list);
	}

}
