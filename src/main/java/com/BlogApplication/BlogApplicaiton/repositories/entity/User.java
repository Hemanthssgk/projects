package com.BlogApplication.BlogApplicaiton.repositories.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 255,nullable = false)
    private String name;
    private String email;
    private String password;
    private String about;

//    @OneToMany
//    @JoinColumn(name = "postId")
//    private List<Post> posts;
//
//    @OneToMany
//    @JoinColumn(name = "roleId")
//    List<Role> roles;
}
