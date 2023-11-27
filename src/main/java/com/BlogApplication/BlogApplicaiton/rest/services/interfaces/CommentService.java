package com.BlogApplication.BlogApplicaiton.rest.services.interfaces;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Comment;
import com.BlogApplication.BlogApplicaiton.rest.services.model.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO saveComment(Comment comment, Integer userId, Integer postId);

    public CommentDTO updateComment(Comment comment,Integer commentId, Integer userId, Integer postId);

    public void deleteComment(Comment comment);
    public List<CommentDTO> getCommentsByUserId(Integer userId);
}
