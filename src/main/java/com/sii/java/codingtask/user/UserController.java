package com.sii.java.codingtask.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public long createUser(@RequestBody final NewUser newUser) {
        NewUserValidator.validate(newUser);
        UserEntity user = UserEntity.builder()
                .balance(newUser.getBalance())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .build();
        UserEntity savedUser = userRepository.save(user);
        log.debug("Created new user with id: " + savedUser.getId());
        return savedUser.getId();
    }

    @PutMapping("/{id}")
    public void deleteUser(@PathVariable final Long id) {
        userRepository.deleteById(id);
        log.debug("delete user with id: " + id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleValidationErrors(final IllegalArgumentException ex) {
        log.trace("IllegalArgumentException: " + ex.getMessage());
        return ResponseEntity.ok().body(ex.getMessage());
    }

}
