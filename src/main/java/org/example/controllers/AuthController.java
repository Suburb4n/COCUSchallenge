package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.config.JwtUtils;
import org.example.config.UserDao;
import org.example.dto.AuthenticationRequest;
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
            return new ResponseEntity(jwtUtils.generateToken(user), HttpStatus.OK);
        }
        return new ResponseEntity<>("Some error has occured",HttpStatus.BAD_REQUEST);
    }
}
