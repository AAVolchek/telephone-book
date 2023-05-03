package com.example.telephonebook.web;

import com.example.telephonebook.AbstractIntegrationTest;
import com.example.telephonebook.TestData;
import com.example.telephonebook.exception.NotFoundException;
import com.example.telephonebook.model.Contact;
import com.example.telephonebook.model.User;
import com.example.telephonebook.repository.ContactRepository;
import com.example.telephonebook.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static com.example.telephonebook.TestData.CONTACT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContactControllerTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        super.abstractSetUp();

        String name = "User";
        String email = "user" + UUID.randomUUID().toString() + "@example.com";
        String password = "password";

        String jwtToken = registerUserAndGetJwtToken(email, name, password);

        headers.setBearerAuth(jwtToken);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found in the database"));

        createContact(user);
    }

    @Test
    void itShouldCheckWhenAddContact() {

        // given
        Contact contact = TestData.getNewContactForAdd();

        // when
        ResponseEntity<Contact> response =
                restTemplate.exchange("http://localhost:"
                        + port + "/api/v1/contacts",
                        HttpMethod.POST,
                        new HttpEntity<>(contact,
                                headers),
                        Contact.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void itShouldCheckWhenGetContactById() {
        // given
        Contact contact = TestData.getNewContact();

        // when
        ResponseEntity<Contact> response = restTemplate.exchange("http://localhost:" +
                        port + "/api/v1/contacts/" +
                        CONTACT_ID,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        Contact.class);

        Contact expected = response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(expected);
        assertEquals(expected, contact);
    }

    @Test
    void itShouldCheckWhenGetAllContacts() {
        // given
        List<Contact> contacts = TestData.getContacts();

        // when
        ResponseEntity<List<Contact>> response = restTemplate.exchange("http://localhost:" +
                port + "/api/v1/contacts",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<Contact>>() {
        });

        List<Contact> expected = response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(expected);
        assertEquals(contacts, expected);

    }

    @Test
    void itShouldCheckWhenUpdateContact() {

        // given
        Contact contact = TestData.getNewContactForAdd();
        contact.setFirstName("Update Name");

        // when
        ResponseEntity<Contact> response = restTemplate.exchange("http://localhost:" +
                port + "/api/v1/contacts/" +
                CONTACT_ID,
                HttpMethod.PUT,
                new HttpEntity<>(contact, headers),
                Contact.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void itShouldCheckWhenJDeleteContact() {
        // given

        // when
        ResponseEntity<Contact> response = restTemplate.exchange("http://localhost:" +
                port + "/api/v1/contacts/" +
                CONTACT_ID,
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Contact.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private void createContact(User user) {
        Contact contact = TestData.getNewContact();
        contact.setUser(user);
        contactRepository.save(contact);
    }

}