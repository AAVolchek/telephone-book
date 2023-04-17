package com.example.telephonebook.service;

import com.example.telephonebook.ContactTestData;
import com.example.telephonebook.model.Contact;
import com.example.telephonebook.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock private ContactRepository contactRepository;
    private ContactService underTest;
    private final long ID = 1L;

    @BeforeEach
    void setUp() {
        underTest = new ContactService(contactRepository);
    }

    @Test
    void canGetAllContacts() {
        // when
        underTest.getAllContacts();
        // then
        verify(contactRepository).findAll();
    }

    @Test
    void canGetContactById() {
        // given
        final Contact contact = mock(Contact.class);
        given(contactRepository.findById(ID)).willReturn(Optional.ofNullable(contact));

        // when
        final Contact actual =  underTest.getContactById(ID);

        // then
        assertNotNull(actual);
        assertEquals(contact, actual);
        verify(contactRepository).findById(ID);

    }

    @Test
    void canSaveContact() {
        // given
        Contact contact = ContactTestData.getNew();

        // when
        underTest.saveContact(contact);

        // then
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);

        verify(contactRepository).save(contactArgumentCaptor.capture());

        Contact capturedContact = contactArgumentCaptor.getValue();
        assertNotNull(capturedContact);
        assertThat(capturedContact).isEqualTo(contact);

    }

    @Test
    void canDeleteContact() {
        // when
        underTest.deleteContact(ID);

        // then
        verify(contactRepository).deleteById(ID);
    }
}