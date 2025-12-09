package com.example.addressbook;


public class AddressBookDBServiceIOAdapter implements AddressBookIOServiceAdapter {

    private AddressBookDBService dbService = new AddressBookDBService();

    @Override
    public void writeData(AddressBook addressBook) {
//        dbService.addContacts(addressBook.getContacts());
    }

    @Override
    public void readData(AddressBook addressBook) {
//        addressBook.setContacts(dbService.getAllContacts());
    }

    @Override
    public long countEntries() {
        return 0;
    }
}
