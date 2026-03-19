package com.example.liquibase.controllers;

import com.example.liquibase.model.Users;
import com.example.liquibase.repository.UserRepo;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<Users>>getUsers(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") int userID){
        return new ResponseEntity<>(userRepo.findById(userID),HttpStatus.OK);
    }
    @PostMapping()
    public Users saveUser(@RequestBody Users user) {
        return userRepo.save(user);
    }
    public Boolean validateName(String name){
        return  name!=null && name.length()>5;
    }
}
