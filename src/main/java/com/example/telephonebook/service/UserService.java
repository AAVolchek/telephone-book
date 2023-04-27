package com.example.telephonebook.service;

import com.example.telephonebook.exception.IllegalRequestDataException;

import com.example.telephonebook.model.User;
import com.example.telephonebook.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        boolean existEmail = userRepository.selectExistsEmail(user.getEmail());
        if (existEmail) {
            throw new IllegalRequestDataException("Email: " + user.getEmail() + " is already registered");
        }
        userRepository.save(user);
    }

}
