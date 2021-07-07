package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.net.URISyntaxException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @Mock
    UserDaoService service;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    UserResource userResource;

    @Test
    void createUser() throws URISyntaxException {
        final Date birthDate = new Date();
        User user = new User(5,"Mark", birthDate);
        when(service.save(argThat(user1 -> {
            assertEquals("Mark", user1.getName());
            assertEquals(5, user1.getId());
            assertEquals(birthDate, user1.getBirthDate());
            return true;
        }))).thenReturn(user);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/users"));
        ResponseEntity<Object> result = userResource.createUser(user, request);
        assertEquals( "http://localhost:8080/users/5" ,result.getHeaders().getLocation().toString());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }


}