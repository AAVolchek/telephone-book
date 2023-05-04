package com.example.telephonebook;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static final long CONTACT_ID = 1L;
    public static final long USER_ID = 1L;

    public static Contact getNewContact() {
        Contact contact = new Contact(
                "Dean",
                "Rob",
                "+12125555550",
                "DeanRobTest@gmail.com",
                LocalDate.of(1991, 5, 6),
                "instagram/Dean13", "");
        contact.setId(CONTACT_ID);
        return contact;
    }

    public static User getNewUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setName("User");
        user.setEmail("user50@gmail.com");
        user.setPassword("password");
        return user;
    }

    public static Contact getNewContactWithUser() {
        Contact contact = getNewContact();
        contact.setUser(getNewUser());
        return contact;
    }

    public static List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(getNewContact());
        return contacts;
    }

    public static Contact getNewContactForAdd() {
        Contact contact = new Contact(
                "Dean",
                "Jee",
                "+12125555550",
                "DeanJee@gmail.com",
                LocalDate.of(1985, 1, 1),
                "instagram/DeanJee2", "");
        contact.setUser(getNewUser());
        return contact;
    }
}
