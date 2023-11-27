package com.BlogApplication.BlogApplicaiton.controllers;

import com.BlogApplication.BlogApplicaiton.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.interfaces.UserService;
import com.BlogApplication.BlogApplicaiton.services.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody User user) throws Exception {
        return  ResponseEntity.ok(userService.updateUser(user,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("DeletedUser",new Date(),true));
    }
}
