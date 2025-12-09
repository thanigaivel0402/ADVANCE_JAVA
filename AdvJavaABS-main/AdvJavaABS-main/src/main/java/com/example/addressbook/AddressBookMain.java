package com.example.addressbook;

import java.util.*;

public class AddressBookMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook("MyBook");

        System.out.print("How many contacts do you want to add? ");
        int count = sc.nextInt();
        sc.nextLine(); 
        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for Contact " + (i + 1) + ":");
            System.out.print("First Name: ");
            String firstName = sc.nextLine();
            System.out.print("Last Name: ");
            String lastName = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("City: ");
            String city = sc.nextLine();
            System.out.print("State: ");
            String state = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = sc.nextLine();
            System.out.print("Zip Code: ");
            int zip = sc.nextInt();
            sc.nextLine();
            Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
            addressBook.addContact(contact);
        }

        System.out.println(count + " contacts added successfully!");

        System.out.println("Contacts in AddressBook:");
        addressBook.getContacts().forEach(System.out::println);
        
        
        System.out.println("Do you want to Edit an existing contact? Y/N");
        String choice = sc.nextLine();
        if(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")) {
        	
        	System.out.println("Enter the firstname you want to edit");
        	String firstname = sc.nextLine();
        	if(firstname!=null) {
        		addressBook.editContact(firstname, new Scanner(System.in));
        	}
        	
        }
        
        
        System.out.println("Do you want to delete an existing contact? Y/N");
        String choice2 = sc.nextLine();
        if(choice2.equalsIgnoreCase("Y") || choice2.equalsIgnoreCase("Yes")) {
        	
        	System.out.println("Enter the firstname you want to delete");
        	String firstname = sc.nextLine();
        	if(firstname!=null) {
        		addressBook.deleteContact(firstname);
        	}
        	
        }
        sc.close();
        
        
        AddressBook book = new AddressBook();

        // Sample Data
        book.addContact(new Contact("Kalki", "Dhandapani","Potheri", "Chennai", "Tamil Nadu", 600001, "9876543210", "kalki@example.com"));
        book.addContact(new Contact("Johhny", "Harish","Guduvancheri", "Bangalore", "Karnataka", 560001, "9876501234", "arun@example.com"));
        book.addContact(new Contact("Meena", "Sharma","kattangulathur", "Mumbai", "Maharashtra", 400001, "9812345678", "meena@example.com"));
        book.addContact(new Contact("Vijay", "Rao", "Sholinganalur","Hyderabad", "Telangana", 500001, "9123456789", "vijay@example.com"));

        // UC11: Sort by Name
        book.sortByName();
        
        // UC12: Sort by City
        book.sortByCity();

        // UC12: Sort by State
        book.sortByState();

        // UC12: Sort by Zip
        book.sortByZip();
    }
}

