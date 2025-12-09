package com.example.addressbook;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class AddressBookJSONService {

    public static final String JSON_FILE = "addressbook.json";
    Gson gson = new Gson();

    // Write address book to JSON file
    public void writeData(List<Contact> contactList) {
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            gson.toJson(contactList, writer);
            System.out.println("JSON File Written Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read address book from JSON file
    public List<Contact> readData() {
        try (FileReader reader = new FileReader(JSON_FILE)) {
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
