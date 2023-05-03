package com.example.telephonebook.repository;

import com.example.telephonebook.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static com.example.telephonebook.TestData.getNewUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenUserEmailExists() {
        // given
        User user = getNewUser();

        underTest.save(user);

        // when
        boolean expected = underTest.selectExistsEmail(user.getEmail());

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenUserEmailDoesNotExists() {
        // given
        String email = "booleanFalse@gmail.com";

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckWhenUserExist() {

        // given
        User user = getNewUser();

        underTest.save(user);

        // when
        User expected = underTest.findByEmail(user.getEmail()).get();

        // then
        assertNotNull(expected);
        assertEquals(user.getId(), expected.getId());
        assertEquals(user.getName(), expected.getName());
        assertEquals(user.getEmail(), expected.getEmail());
        assertEquals(user.getPassword(), expected.getPassword());

    }

}