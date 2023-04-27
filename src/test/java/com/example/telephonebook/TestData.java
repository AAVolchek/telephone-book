package com.example.telephonebook;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.model.User;

import java.time.LocalDate;

public class TestData {

    public static final long CONTACT_ID = 1L;
    public static final long USER_ID = 1000L;

    public static Contact getNewContact() {
        return new Contact(
                "Dean",
                "Rob",
                "+12125555550",
                "DeanRobTest@gmail.com",
                LocalDate.of(1991, 5, 6),
                "instagram/Dean13");
    }

    public static User getNewUser() {
        User user = new User();
        user.setName("User");
        user.setEmail("user@gmail.com");
        user.setPassword("password");
        return user;
    }
}
