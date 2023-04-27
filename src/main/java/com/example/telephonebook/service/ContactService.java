package com.example.telephonebook.service;

import com.example.telephonebook.model.Contact;
import com.example.telephonebook.repository.ContactRepository;
import com.example.telephonebook.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.telephonebook.util.Validation.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class ContactService {

    private ContactRepository contactRepository;
    private final UserRepository userRepository;

    public List<Contact> getAllContacts(Long usedId) {
        return contactRepository.getAll(usedId);
    }

    public Contact getContactById(Long id, Long userId) {
        checkNotFoundWithId(contactRepository.selectExistsUser(id, userId), id);
        return contactRepository.get(id, userId);
    }

    @Transactional
    public void saveContact(Contact contact, Long userId) {
        if (contact.getId() != null && contactRepository.get(contact.getId(), userId) == null) {
            throw new IllegalArgumentException("Contact does not exist or does not belong to the user");
        }
        checkNotFoundWithId(userRepository.existsById(userId), userId);
        contact.setUser(userRepository.findById(userId).orElse(null));
        contactRepository.save(contact);
    }

    public void deleteContact(Long id, Long userId) {

        checkNotFoundWithId(contactRepository.selectExistsUser(id, userId), id);
        contactRepository.delete(id, userId);
    }

}
