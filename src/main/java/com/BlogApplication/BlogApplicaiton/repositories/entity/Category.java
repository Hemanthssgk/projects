//package com.BlogApplication.BlogApplicaiton.repositories.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer categoryId;
//    private String title;
//    @OneToMany
//    @JoinColumn(name = "postId")
//    private List<Post> posts;
//}
