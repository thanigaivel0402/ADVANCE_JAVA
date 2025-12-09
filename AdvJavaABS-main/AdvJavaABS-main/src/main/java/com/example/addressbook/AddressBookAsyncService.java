package com.example.addressbook;


import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;

public class AddressBookAsyncService {
    private final AddressBookDBService dbService = new AddressBookDBService();
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public CompletableFuture<List<ContactPerson>> readAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return dbService.readAllContacts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    public CompletableFuture<Boolean> addContactAsync(ContactPerson contact) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return dbService.addContact(contact);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
