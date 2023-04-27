package com.example.telephonebook.repository;

import com.example.telephonebook.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@AllArgsConstructor
public class ContactRepository {

    private final JpaContactRepository jpaContactRepository;

    @Transactional
    public Contact save(Contact contact) {
        return jpaContactRepository.save(contact);
    }

    public void delete(Long id, Long userId) {
        jpaContactRepository.delete(id, userId);
    }

    public Contact get(Long id, Long userId) {
        return jpaContactRepository.findById(id)
                .filter(contact -> contact.getUser().getId().equals(userId))
                .orElse(null);
    }

    public List<Contact> getAll(Long userId) {
        return jpaContactRepository.getAll(userId);
    }

    public Contact getWithUser(Long id, Long userId) {
        return jpaContactRepository.getWithUser(id, userId);
    }

    public boolean selectExistsUser(Long id, Long userId) {
        return jpaContactRepository.selectExistsUser(id, userId);
    }
}
