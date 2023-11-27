package com.BlogApplication.BlogApplicaiton.rest.repositories;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUser_UserId(Integer id);
}
