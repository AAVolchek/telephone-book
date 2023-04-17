package com.example.telephonebook;

import com.example.telephonebook.model.Contact;

import java.time.LocalDate;

public class ContactTestData {

    public static Contact getNew(){
        return new Contact(null,"Dean","Rob", "+12125555550", "DeanRobTest@gmail.com",LocalDate.of(1991,5,6),"instagram/Dean13");
    }
}
