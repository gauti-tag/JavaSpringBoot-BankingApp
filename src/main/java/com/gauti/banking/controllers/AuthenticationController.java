package com.gauti.banking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauti.banking.dto.AuthenticationRequest;
import com.gauti.banking.dto.AuthenticationResponse;
import com.gauti.banking.dto.UserDto;
import com.gauti.banking.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }

    // Action to authenticate a user
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        // dedicate this below treatment to the user service
        return ResponseEntity.ok(userService.authenticate(request));

        /*      */

        // need auth manager using authenticate method
        //authManager.authenticate(
        //    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        //);

        // find the user and get the ID
        //final UserDetails user = userRepository.findByEmail(request.getEmail()).get(); // Get Id of the object
        //String token = jwtUtils.generateToken(user); // Generate the token after finding the user
        //return ResponseEntity.ok(
        //    AuthenticationResponse.builder()
        //        .token(token)
        //        .build()
        //);

    }

}
