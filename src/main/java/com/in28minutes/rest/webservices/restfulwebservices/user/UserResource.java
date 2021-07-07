package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserResource {

    private final UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null)
            throw  new UserNotFoundException("id-"+id);
        return user;
    }

    //
    // input - details of user
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, HttpServletRequest request) throws URISyntaxException {
        User savedUser = service.save(user);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        URI location = new URI(request.getRequestURL().toString()+ "/" + savedUser.getId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(user == null)
            throw  new UserNotFoundException("id-"+id);
    }

}









