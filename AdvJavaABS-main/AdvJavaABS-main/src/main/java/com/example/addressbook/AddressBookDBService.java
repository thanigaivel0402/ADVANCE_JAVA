package com.example.addressbook;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService implements AddressBookIO{
	

	public boolean addContact(ContactPerson contact) throws SQLException {
	    String sql = "INSERT INTO address_book_contact (first_name,last_name,address,city,state,zip,phone,email,date_added) VALUES (?,?,?,?,?,?,?,?,?)";

	    try (Connection conn = DBConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, contact.getFirstName());
	        ps.setString(2, contact.getLastName());
	        ps.setString(3, contact.getAddress());
	        ps.setString(4, contact.getCity());
	        ps.setString(5, contact.getState());
	        ps.setString(6, contact.getZip());
	        ps.setString(7, contact.getPhone());
	        ps.setString(8, contact.getEmail());
	        ps.setTimestamp(9, Timestamp.valueOf(contact.getDateAdded()));

	        return ps.executeUpdate() == 1;
	    }
	}

	public void addContacts(List<ContactPerson> contacts) {
	    contacts.forEach(contact -> {
	        try {
	            this.addContact(contact);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    });
	}

	


    public List<ContactPerson> readAllContacts() throws SQLException {
    	
        List<ContactPerson> list = new ArrayList<>();
        String sql = "SELECT * FROM address_book_contact";
        try (Connection conn = DBConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ContactPerson c = new ContactPerson();
                c.setId(rs.getInt("id"));
                c.setFirstName(rs.getString("first_name"));
                c.setLastName(rs.getString("last_name"));
                c.setAddress(rs.getString("address"));
                c.setCity(rs.getString("city"));
                c.setState(rs.getString("state"));
                c.setZip(rs.getString("zip"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                Timestamp ts = rs.getTimestamp("date_added");
                if (ts != null) c.setDateAdded(ts.toLocalDateTime());
                list.add(c);
            }
        }
        return list;
    }
    
    
    
 // inside AddressBookDBService class
    public int updateContact(String firstName, String lastName, ContactPerson updated) throws SQLException {
        String sql = "UPDATE address_book_contact SET address=?, city=?, state=?, zip=?, phone=?, email=? WHERE first_name=? AND last_name=?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, updated.getAddress());
            ps.setString(2, updated.getCity());
            ps.setString(3, updated.getState());
            ps.setString(4, updated.getZip());
            ps.setString(5, updated.getPhone());
            ps.setString(6, updated.getEmail());
            ps.setString(7, firstName);
            ps.setString(8, lastName);
            return ps.executeUpdate();
        }
    }

    // helper to read single by name
    public ContactPerson getContactByName(String firstName, String lastName) throws SQLException {
        String sql = "SELECT * FROM address_book_contact WHERE first_name=? AND last_name=?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ContactPerson c = new ContactPerson();
                    c.setId(rs.getInt("id"));
                    c.setFirstName(rs.getString("first_name"));
                    c.setLastName(rs.getString("last_name"));
                    c.setAddress(rs.getString("address"));
                    c.setCity(rs.getString("city"));
                    c.setState(rs.getString("state"));
                    c.setZip(rs.getString("zip"));
                    c.setPhone(rs.getString("phone"));
                    c.setEmail(rs.getString("email"));
                    Timestamp ts = rs.getTimestamp("date_added");
                    if (ts != null) c.setDateAdded(ts.toLocalDateTime());
                    return c;
                }
            }
        }
        return null;
    }

    public List<ContactPerson> getContactsInDateRange(LocalDateTime from, LocalDateTime to) throws SQLException {
        List<ContactPerson> list = new ArrayList<>();
        String sql = "SELECT * FROM address_book_contact WHERE date_added BETWEEN ? AND ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(from));
            ps.setTimestamp(2, Timestamp.valueOf(to));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToContact(rs));
            }
        }
        return list;
    }

    private ContactPerson mapResultSetToContact(ResultSet rs) throws SQLException {
        ContactPerson c = new ContactPerson();
        c.setId(rs.getInt("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setAddress(rs.getString("address"));
        c.setCity(rs.getString("city"));
        c.setState(rs.getString("state"));
        c.setZip(rs.getString("zip"));
        c.setPhone(rs.getString("phone"));
        c.setEmail(rs.getString("email"));
        Timestamp ts = rs.getTimestamp("date_added");
        if (ts != null) c.setDateAdded(ts.toLocalDateTime());
        return c;
    }
    
    public int countByCity(String city) throws SQLException {
        String sql = "SELECT COUNT(*) FROM address_book_contact WHERE city = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, city);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public int countByState(String state) throws SQLException {
        String sql = "SELECT COUNT(*) FROM address_book_contact WHERE state = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, state);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
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

