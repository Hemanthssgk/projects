//package com.BlogApplication.BlogApplicaiton.repositories.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer commentId;
//    private String comment;
//
//    @OneToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    @OneToOne
//    @JoinColumn(name = "postId")
//    private Post post;
//}
