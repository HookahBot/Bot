package edu.bot.hookahBot.users.controllers;

import edu.bot.hookahBot.users.dao.models.User;
import edu.bot.hookahBot.users.dao.repositories.MetaModelUserRepository;
import edu.bot.hookahBot.users.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private MetaModelUserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.get(id).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        userRepository.save(user);
        URI createdUserUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(createdUserUri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable long id,
                                     @RequestBody User user) {
        user.setId(id);
        userRepository.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
        User user = userRepository.get(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return ResponseEntity.ok(user);
    }
}