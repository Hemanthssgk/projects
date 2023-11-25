package com.BlogApplication.BlogApplicaiton.controllers;

import com.BlogApplication.BlogApplicaiton.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.repositories.entity.Comment;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.interfaces.CommentService;
import com.BlogApplication.BlogApplicaiton.services.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<CommentDTO> saveComment(@RequestBody Comment comment, @PathVariable Integer userId, @PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.saveComment(comment, userId, postId));
    }

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDTO> saveCommentByAnonymousUser(@RequestBody Comment comment, @PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.saveComment(comment, null, postId));
    }

    @DeleteMapping("/post/{PostId}/comment")
    public ResponseEntity<ApiResponse> deleteComment(@RequestBody Comment comment)
    {
        commentService.deleteComment(comment);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted", new Date(),true), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/comment")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    @PutMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody Comment comment, @PathVariable Integer userId, @PathVariable Integer postId)
    {
        return ResponseEntity.ok(commentService.updateComment(comment, comment.getCommentId(), userId,postId));
    }
}
