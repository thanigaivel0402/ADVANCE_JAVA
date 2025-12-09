package com.example.addressbook;

public interface AddressBookIOServiceAdapter {
    void writeData(AddressBook addressBook);
    void readData(AddressBook addressBook);
    long countEntries();
}
