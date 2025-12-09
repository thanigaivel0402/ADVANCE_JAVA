package com.example.addressbook;


import java.util.List;

public interface AddressBookIO {
    void write(List<ContactPerson> contacts);
    List<ContactPerson> read();
    void print();
    long count();
}

