package com.foodcart.controller;

import com.foodcart.model.User;
import com.foodcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    ResponseEntity<String> signup(@RequestBody User user) {

        try{
            String email = user.getEmail();
            String password = user.getPassword();

            // Check if the email is already registered
            if(userRepository.existsByEmail(email)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
            }

            // encrypt the password
            //String encryptedPassword = passwordEncoder.encode(password);

            // creating a new entity of user and setting email and password
            User user1 = new User();
            user1.setEmail(email);
            user1.setPassword(password);

            // saving the user entity in DB
            userRepository.save(user1);

            // return the success response
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Server Error");
        }
    }
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody User user) {

        try{
            String email = user.getEmail();
            String password = user.getPassword();

            // if the user is not registered
            if(userRepository.findByEmail(email).isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email does not exist");
            }
            if(password.equals(userRepository.findByEmail(email).get().getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body("Login successful");
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }
}