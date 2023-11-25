package com.BlogApplication.BlogApplicaiton.repositories.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @NotEmpty(message = "name mustn't be empty")
    private String name;
    @Email(message = "Wrong email Address Provided")
    private String email;
    @NotEmpty(message = "password mustn't be empty")
    @Size(min = 4, max = 12,message = "Password should be min of 4 chars and max of 12")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[\\W_])(?=.*[a-zA-Z0-9]).{4,}$", message = "password should have at-least one uppercase, special char, numbers...")
    private String password;
    @NotEmpty(message = "name mustn't be empty")
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;

    // deleting or updating or doing whatever to the user will update the comments as well, if user is deleted then comment is also deleted.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();
}
