package com.example.telephonebook.service;

import com.example.telephonebook.TestData;
import com.example.telephonebook.model.Contact;
import com.example.telephonebook.repository.ContactRepository;
import com.example.telephonebook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.telephonebook.TestData.CONTACT_ID;
import static com.example.telephonebook.TestData.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;
    @Mock
    private UserRepository userRepository;
    private ContactService underTest;


    @BeforeEach
    void setUp() {
        underTest = new ContactService(userRepository, contactRepository);
    }

    @Test
    void canGetAllContacts() {
        // when
        underTest.getAllContacts(USER_ID);
        // then
        verify(contactRepository).getAll(USER_ID);
    }

    @Test
    void canGetContactById() {
        // given
        final Contact contact = mock(Contact.class);
        given(contactRepository.get(CONTACT_ID, USER_ID)).willReturn(contact);
        given(contactRepository.selectExistsUser(CONTACT_ID, USER_ID))
                .willReturn(true);

        // when
        final Contact actual = underTest.getContactById(CONTACT_ID, USER_ID);

        // then
        assertNotNull(actual);
        assertEquals(contact, actual);
        verify(contactRepository).get(CONTACT_ID, USER_ID);

    }

    @Test
    void itShouldCheckWhenSaveContact() {
        // given
        Contact contact = TestData.getNewContactForAdd();
        given(userRepository.existsById(USER_ID)).willReturn(true);

        // when
        underTest.saveContact(contact, USER_ID);

        // then
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact capturedContact = contactArgumentCaptor.getValue();
        assertNotNull(capturedContact);
        assertThat(capturedContact).isEqualTo(contact);

    }

    @Test
    void itShouldCheckWhenDeleteContact() {
        // given
        given(contactRepository.selectExistsUser(CONTACT_ID, USER_ID))
                .willReturn(true);

        // when
        underTest.deleteContact(CONTACT_ID, USER_ID);

        // then
        verify(contactRepository).delete(CONTACT_ID, USER_ID);
    }
}