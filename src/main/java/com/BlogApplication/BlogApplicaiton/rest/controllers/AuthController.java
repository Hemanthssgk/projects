package com.BlogApplication.BlogApplicaiton.rest.controllers;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.UserService;
import com.BlogApplication.BlogApplicaiton.rest.services.model.UserDTO;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> LoginUser() throws Exception {
        String JWTToken = userService.loginuser();
        return ResponseEntity.ok(new ApiResponse(JWTToken,new Date(),true));
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
