package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.config.JwtUtils;
import org.example.config.UserDao;
import org.example.dto.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(TripController.class);
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email, request.password
                )
        );
        final UserDetails user = userDao.findUserByEmail(request.email);
        if (user != null) {
            logger.info("Authentication successful");
            return new ResponseEntity(jwtUtils.generateToken(user), HttpStatus.OK);
        }
        logger.warn("Authentication failed! Please check your credentials");
        return new ResponseEntity<>("Some error has occured",HttpStatus.BAD_REQUEST);
    }
}
