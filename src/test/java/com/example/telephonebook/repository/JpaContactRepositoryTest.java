package com.example.telephonebook.repository;

import com.example.telephonebook.TestData;
import com.example.telephonebook.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static com.example.telephonebook.TestData.CONTACT_ID;
import static com.example.telephonebook.TestData.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class JpaContactRepositoryTest {

    @Autowired
    private JpaContactRepository underTest;
    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldCheckWhenContactDelete() {
        // given
        Contact contact = TestData.getNewContactWithUser();

        // when
        saveContact(contact);

        boolean existed = underTest.selectExistsUser(CONTACT_ID, USER_ID);

        underTest.delete(CONTACT_ID, USER_ID);

        boolean expected = underTest.selectExistsUser(CONTACT_ID, USER_ID);

        // then
        assertThat(existed).isTrue();
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckWhenGetAll() {
        // given
        Contact contact = TestData.getNewContactWithUser();

        // when
        saveContact(contact);

        List<Contact> contactList = underTest.getAll(USER_ID);

        // then
        assertNotNull(contactList);
        assertEquals(1, contactList.size());
    }

    @Test
    void itShouldCheckWhenGetWithUser() {
        // given
        Contact contact = TestData.getNewContactWithUser();

        // when
        saveContact(contact);

        Contact expected = underTest.getWithUser(CONTACT_ID, USER_ID);

        // then
        assertNotNull(expected);
        assertEquals(contact, expected);
    }

    @Test
    void itShouldCheckWhenSelectExistsUser() {
        // given
        Contact contact = TestData.getNewContactWithUser();

        // when
        saveContact(contact);

        boolean expected = underTest.selectExistsUser(CONTACT_ID, USER_ID);

        // then
        assertThat(expected).isTrue();
    }

    private void saveContact(Contact contact) {
        userRepository.save(contact.getUser());
        underTest.save(contact);
    }
}