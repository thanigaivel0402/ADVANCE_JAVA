package com.example.addressbook;


import java.time.LocalDateTime;
import java.util.Objects;

public class ContactPerson {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private LocalDateTime dateAdded;

    public ContactPerson() {}

    // getters & setters for all fields (generate in IDE)

    // convenience constructor
    public ContactPerson(String firstName, String lastName, String address, String city,
                         String state, String zip, String phone, String email) {
        this.firstName = firstName; this.lastName = lastName; this.address = address;
        this.city = city; this.state = state; this.zip = zip;
        this.phone = phone; this.email = email;
        this.dateAdded = LocalDateTime.now();
    }

    // equals and hashCode consider business fields (not id/dateAdded)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactPerson)) return false;
        ContactPerson that = (ContactPerson) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(address, that.address) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(zip, that.zip) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, state, zip, phone, email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + phone + ", " + email + ")";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

}
