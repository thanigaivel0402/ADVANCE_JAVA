package com.example.addressbook;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookDBUpdateTest {

    @Test
    public void givenUpdatedContact_WhenSyncedWithDB_ShouldMatch() throws Exception {
        AddressBookDBService db = new AddressBookDBService();

        // first ensure person exists or insert one (for test isolation)
        ContactPerson person = new ContactPerson("John","Doe","OldAddr","City","State","12345","9999999999","john@example.com");
        db.addContact(person); // see UC20 implementation (or ensure exists)

        // prepare updated object
        ContactPerson updated = new ContactPerson("John","Doe","NewAddr","NewCity","NewState","54321","1112223333","john.new@example.com");

        int changed = db.updateContact("John","Doe",updated);
        assertEquals(1, changed);

        ContactPerson fromDb = db.getContactByName("John","Doe");
        assertNotNull(fromDb);

        // use equals to compare (equals ignores id and dateAdded)
        assertEquals(updated, fromDb);
    }
}
