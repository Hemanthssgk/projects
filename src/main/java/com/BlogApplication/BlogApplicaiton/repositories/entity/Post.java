//package com.BlogApplication.BlogApplicaiton.repositories.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.sql.Blob;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Post {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer postId;
//    private String title;
//    private String content;
//    private Blob image;
//
//    @OneToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    @OneToMany
//    @JoinColumn(name = "commentId")
//    List<Comment> comments;
//
//    @OneToOne
//    @JoinColumn(name = "categoryId")
//    private Category category;
//}
