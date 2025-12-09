package com.example.addressbook;

import java.time.LocalDateTime;
import java.util.Objects;

public class Contact {
    private String firstName, lastName, address, city, state, email;
    private String phoneNumber;
    private int zip;
    private String zip1;
    private String phone;
    private LocalDateTime dateAdded;
    

    public Contact(String firstName, String lastName, String address, String city,
                   String state, int zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact() {}

    // getters & setters for all fields (generate in IDE)

    // convenience constructor
    public Contact(String firstName, String lastName, String address, String city,
                         String state, String zip1, String phone, String email) {
        this.firstName = firstName; this.lastName = lastName; this.address = address;
        this.city = city; this.state = state; this.zip1 = zip1;
        this.phone = phone; this.email = email;
        this.dateAdded = LocalDateTime.now();
    }
    @Override
   
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactPerson)) return false;
        Contact that = (Contact) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(address, that.address) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(zip, that.zip) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(email, that.email);
    }

    public String getZip1() {
		return zip1;
	}

	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, state, zip, phone, email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + phone + ", " + email + ")";
    }


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}

