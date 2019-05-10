package com.aggy.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
         User userResult = userDao.save(user);

         URI location = ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(userResult.getId() ).toUri();
         return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = userDao.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }
}
