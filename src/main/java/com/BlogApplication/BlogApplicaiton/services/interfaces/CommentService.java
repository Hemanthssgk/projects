package com.BlogApplication.BlogApplicaiton.services.interfaces;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Comment;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.model.CommentDTO;

import java.util.List;

public interface CommentService {
    public CommentDTO saveComment(Comment comment, Integer userId, Integer postId);

    public CommentDTO updateComment(Comment comment,Integer commentId, Integer userId, Integer postId);

    public void deleteComment(Comment comment);
    public List<CommentDTO> getCommentsByUserId(Integer userId);
}
