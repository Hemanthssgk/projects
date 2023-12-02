package com.BlogApplication.BlogApplicaiton.rest.controllers;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.UserService;
import com.BlogApplication.BlogApplicaiton.rest.services.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        String JWTToken = userService.generateJWTForLoggedInUser();
        return ResponseEntity.ok(new ApiResponse(JWTToken,new Date(),true));
    }
}
