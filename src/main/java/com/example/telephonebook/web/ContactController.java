package com.example.telephonebook.web;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact existingContact = contactService.getContactById(id);
        if (existingContact != null) {
            contact.setId(id);
            contactService.saveContact(contact);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
