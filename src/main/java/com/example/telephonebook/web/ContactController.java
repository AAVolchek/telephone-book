package com.example.telephonebook.web;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.service.ContactService;
import com.example.telephonebook.util.SecurityUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        Long userId = SecurityUserUtil.authUserId();
        return contactService.getAllContacts(userId);
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        Long userId = SecurityUserUtil.authUserId();
        return contactService.getContactById(id, userId);
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        Long userId = SecurityUserUtil.authUserId();
        contactService.saveContact(contact, userId);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Long userId = SecurityUserUtil.authUserId();
        Contact existingContact = contactService.getContactById(id, userId);
        if (existingContact != null) {
            contact.setId(id);
            contactService.saveContact(contact, userId);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        Long userId = SecurityUserUtil.authUserId();
        contactService.deleteContact(id, userId);
    }

}
