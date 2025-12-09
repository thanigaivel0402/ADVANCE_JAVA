package com.example.addressbook;


import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookDBServiceTest {

    @Test
    public void givenDB_WhenReadAllContacts_ShouldReturnList() throws Exception {
        AddressBookDBService service = new AddressBookDBService();
        List<ContactPerson> contacts = service.readAllContacts();
        assertNotNull(contacts);
        // optional: assertTrue(contacts.size() >= 0);
    }
}
