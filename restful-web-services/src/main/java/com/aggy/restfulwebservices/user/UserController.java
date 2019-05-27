package com.aggy.restfulwebservices.user;

import com.aggy.restfulwebservices.user.post.Post;
import com.aggy.restfulwebservices.user.post.PostRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource retrieveUser(@PathVariable Integer id) {
        try {

            Optional<User> userOpt = userRepository.findById(id);

            User user = null;
            if(userOpt.isPresent()) {
                user = userOpt.get();
            }

            Resource<User> resource = new Resource<>(user);
            ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
            resource.add(linkTo.withRel("all-users"));
            return resource;
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("id: " + id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
         User userResult = userRepository.save(user);

         URI location = ServletUriComponentsBuilder
                 .fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(userResult.getId() ).toUri();
         return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {

        Optional<User> userOpt = userRepository.findById(id);

        User user = null;
        if(userOpt.isPresent()) {
            user = userOpt.get();
        }


        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        userRepository.delete(user);
    }

    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsByUser(@PathVariable Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        User user = null;
        if(userOpt.isPresent()) {
            user = userOpt.get();
        }


        if(user == null) {
            throw new UserNotFoundException("id-" + userId);
        }

        return postRepository.findByUserId(userId);

    }
}
