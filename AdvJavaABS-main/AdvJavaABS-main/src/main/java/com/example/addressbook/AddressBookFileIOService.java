package com.example.addressbook;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AddressBookFileIOService implements AddressBookIO {

    public static final String ADDRESSBOOK_FILE = "addressbook-file.txt";

    // ✅ UC13 — Write Data to File
    public void writeData(List<Contact> contactList) {
        StringBuffer buffer = new StringBuffer();

        contactList.forEach(contact -> buffer.append(contact.toString()).append("\n"));

        try {
            Files.write(Paths.get(ADDRESSBOOK_FILE), buffer.toString().getBytes());
            System.out.println("Contacts successfully written to File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ UC13 — Read Data From File
    public List<String> readData() {
        try {
            return Files.readAllLines(Paths.get(ADDRESSBOOK_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ UC13 — Print Data
    public void printData() {
        try {
            Files.lines(Paths.get(ADDRESSBOOK_FILE))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ UC13 — Count Entries
    public long countEntries() {
        try {
            return Files.lines(Paths.get(ADDRESSBOOK_FILE)).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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
