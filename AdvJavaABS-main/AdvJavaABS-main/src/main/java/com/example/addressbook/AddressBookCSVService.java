package com.example.addressbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AddressBookCSVService implements AddressBookIO{

    public static final String CSV_FILE = "addressbook.csv";

    //  Write contacts to CSV file
    public void writeData(List<Contact> contactList) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            List<String[]> data = new ArrayList<>();

            data.add(new String[]{"First Name", "Last Name", "City", "State", "Phone", "Email"});

            for (Contact c : contactList) {
                data.add(new String[]{
                        c.getFirstName(),
                        c.getLastName(),
                        c.getCity(),
                        c.getState(),
                        c.getPhoneNumber(),
                        c.getEmail()
                });
            }
            writer.writeAll(data);
            System.out.println("CSV File Written Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Read contacts from CSV file
    public List<String[]> readData() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE))) {
            return reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public void write(List<ContactPerson> contacts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ContactPerson> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
