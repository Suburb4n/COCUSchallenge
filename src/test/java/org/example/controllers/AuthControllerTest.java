package org.example.controllers;

import org.example.config.JwtUtils;
import org.example.config.UserDao;
import org.example.dto.AuthenticationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private AuthenticationManager mockAuthenticationManager;

    private UserDao mockUserDao;

    private JwtUtils mockJwtUtils;


    private AuthController authController;

    @BeforeEach
    public void setUp() {
        mockAuthenticationManager = mock(AuthenticationManager.class);
        mockUserDao = mock(UserDao.class);
        mockJwtUtils = mock(JwtUtils.class);
        authController = new AuthController(mockAuthenticationManager, mockUserDao, mockJwtUtils);
    }

    @Test
    public void testAuthenticate_successfulAuthentication_shouldReturnToken() {
        //Arrange
        String email = "dluis1651@gmail.com";
        String password = "password";
        AuthenticationRequest request = new AuthenticationRequest();
        request.email = email;
        request.password = password;
        UserDetails userDetails = new User(email, password, Collections.emptyList());

        when(mockAuthenticationManager.authenticate(any())).thenReturn(null);
        when(mockUserDao.findUserByEmail(email)).thenReturn(userDetails);
        when(mockJwtUtils.generateToken(userDetails)).thenReturn("mocked_token");

        //Act
        ResponseEntity<String> response = authController.authenticate(request);
        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mocked_token", response.getBody());
        verify(mockAuthenticationManager).authenticate(any());
        verify(mockUserDao).findUserByEmail(email);
        verify(mockJwtUtils).generateToken(userDetails);
    }

    @Test
    public void testAuthenticate_userNotFound() {
        //Arrange
        String email = "nonexistinguser@example.com";
        String password = "password";
        AuthenticationRequest request = new AuthenticationRequest();
        request.email = email;
        request.password = password;

        when(mockAuthenticationManager.authenticate(any())).thenReturn(null);
        when(mockUserDao.findUserByEmail(email)).thenReturn(null);
        //Act
        ResponseEntity<String> response = authController.authenticate(request);
        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Some error has occured", response.getBody());
        verify(mockAuthenticationManager).authenticate(any());
        verify(mockUserDao).findUserByEmail(email);
        verify(mockJwtUtils, never()).generateToken(any());
    }
}
