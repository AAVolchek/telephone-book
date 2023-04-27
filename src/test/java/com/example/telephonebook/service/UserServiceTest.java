package com.example.telephonebook.service;

import com.example.telephonebook.model.User;
import com.example.telephonebook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.telephonebook.TestData.USER_ID;
import static com.example.telephonebook.TestData.getNewUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService underTest;


    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }

    @Test
    void canSaveUser() {

        // given
        User user = getNewUser();

        // when
        underTest.saveUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertNotNull(capturedUser);
        assertThat(capturedUser).isEqualTo(user);
    }

}