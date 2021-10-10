package com.carScan.assignment.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carScan.assignment.config.TokenConfig;
import com.carScan.assignment.exception.CarScanErrorObject;
import com.carScan.assignment.models.AuthenticationRequest;
import com.carScan.assignment.models.AuthenticationResponse;
import com.carScan.assignment.service.UserServiceImpl;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private UserServiceImpl userServiceImpl;

    Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {

        logger.info("Login Request = " + authenticationRequest);

        authenticate(authenticationRequest.getMobileNumber(), authenticationRequest.getPassword());

        final UserDetails userDetails = userServiceImpl
                .loadUserByUsername(authenticationRequest.getMobileNumber());

        final String token = tokenConfig.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials");
            throw new CarScanErrorObject(HttpStatus.BAD_REQUEST, "Invalid Credentials");
        }
    }
}
