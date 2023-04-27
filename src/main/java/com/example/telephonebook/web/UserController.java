package com.example.telephonebook.web;
import com.example.telephonebook.exception.NotFoundException;
import com.example.telephonebook.model.User;
import com.example.telephonebook.repository.UserRepository;
import com.example.telephonebook.util.SecurityUserUtil;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository repository;

    @GetMapping("/authUser")
    public User getAuthUser(){
        Long userId = SecurityUserUtil.authUserId();
        return repository.findById(userId).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s not found", userId)));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user){
        boolean userExist = repository.existsById(id);
        if(userExist){
            user.setId(id);
            repository.save(user);
        }
    }

}
