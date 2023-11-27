package com.BlogApplication.BlogApplicaiton.rest.repositories.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 255,nullable = false)
    @NotEmpty(message = "name mustn't be empty")
    private String name;
    @Email(message = "Wrong email Address Provided")
    private String email;
    @NotEmpty(message = "password mustn't be empty")
    @Size(min = 4,message = "Password should be min of 4 chars")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[\\W_])(?=.*[a-zA-Z0-9]).{4,}$", message = "password should have at-least one uppercase, special char, numbers...")
    private String password;
    @NotEmpty(message = "name mustn't be empty")
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts;

    // deleting or updating or doing whatever to the user will update the comments as well, if user is deleted then comment is also deleted.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "roleJoinTable",
        joinColumns = @JoinColumn(name = "userId" ),
        inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    Set<Role> roles = new HashSet<>();
}
