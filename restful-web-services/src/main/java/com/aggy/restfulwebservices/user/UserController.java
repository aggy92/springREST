package com.aggy.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        try {

            User user = userDao.find(id);
            return user;
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("id: " + id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
         User userResult = userDao.save(user);

         URI location = ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(userResult.getId() ).toUri();
         return ResponseEntity.created(location).build();
    }
}
