package com.example.addressbook;


import java.util.*;

public class AddressBook {
	public enum IOService { CONSOLE_IO, FILE_IO }

    private String name;
    private List<Contact> contacts;
    
    public AddressBook() {
    	
    }

    public AddressBook(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        if (contacts.contains(contact)) {
            System.out.println("Duplicate Contact Not Allowed");
            return false;
        }
        contacts.add(contact);
        return true;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getName() { return name; }
    
    public boolean editContact(String firstName, Scanner sc) {
        for (Contact c : contacts) {
            if (c.getFirstName().equalsIgnoreCase(firstName)) {
                System.out.print("Enter new City: ");
                c.setCity(sc.nextLine());
                System.out.print("Enter new State: ");
                c.setState(sc.nextLine());
                System.out.print("Enter new Zip: ");
                c.setZip(Integer.parseInt(sc.nextLine()));
                System.out.print("Enter new Phone: ");
                c.setPhoneNumber(sc.nextLine());
                System.out.print("Enter new Email: ");
                c.setEmail(sc.nextLine());
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteContact(String firstName) {
        return contacts.removeIf(c -> c.getFirstName().equalsIgnoreCase(firstName));
    }
    
 // UC11: Sort by Name (using Collections & Streams)
    public void sortByName() {
        System.out.println("---- Sorting by Name ----");
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName)
                        .thenComparing(Contact::getLastName))
                .forEach(System.out::println);
    }

 // UC12: Sort by City
    public void sortByCity() {
        System.out.println("---- Sorting by City ----");
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .forEach(System.out::println);
    }

    // UC12: Sort by State
    public void sortByState() {
        System.out.println("---- Sorting by State ----");
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .forEach(System.out::println);
    }

    // UC12: Sort by Zip
    public void sortByZip() {
        System.out.println("---- Sorting by Zip ----");
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .forEach(System.out::println);
    }
    
    // UC13 : Ability to Read or Write the Address Book with Persons Contact into aFile using File IO 
    public void writeAddressBook(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new AddressBookFileIOService().writeData(contacts);
        }
    }

    public void printAddressBook(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new AddressBookFileIOService().printData();
        }
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new AddressBookFileIOService().countEntries();
        }
        return 0;
    }

    public void readAddressBook(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            List<String> data = new AddressBookFileIOService().readData();
            data.forEach(System.out::println);
        }
    }

    
    // UC14 - CSV Read/Write using OpenCSV
    public void writeCSV() {
        new AddressBookCSVService().writeData(contacts);
    }

    public void readCSV() {
        List<String[]> data = new AddressBookCSVService().readData();
        data.forEach(row -> System.out.println(String.join(" | ", row)));
    }

    // UC15 -  JSON Read/Write using GSON
    public void writeJSON() {
        new AddressBookJSONService().writeData(contacts);
    }

    public void readJSON() {
        List<Contact> list = new AddressBookJSONService().readData();
        list.forEach(System.out::println);
    }

}

