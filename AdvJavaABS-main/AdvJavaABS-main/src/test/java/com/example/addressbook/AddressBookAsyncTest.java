package com.example.addressbook;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
public class AddressBookAsyncTest {

	@Test
	public void givenAsyncRead_WhenCompleted_ShouldReturnResult() throws Exception {
	    AddressBookAsyncService async = new AddressBookAsyncService();
	    CompletableFuture<List<ContactPerson>> future = async.readAllAsync();
	    List<ContactPerson> list = future.get(10, TimeUnit.SECONDS);
	    assertNotNull(list);
	    async.shutdown();
	}

}
