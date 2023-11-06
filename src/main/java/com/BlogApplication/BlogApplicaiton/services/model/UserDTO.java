package com.BlogApplication.BlogApplicaiton.services.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String name;
    private String email;
    private String about;

//    private List<Post> posts;
//
//    List<Role> roles;
}
