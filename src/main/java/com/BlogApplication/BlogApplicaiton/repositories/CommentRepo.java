package com.BlogApplication.BlogApplicaiton.repositories;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Comment;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUser_UserId(Integer id);
}
