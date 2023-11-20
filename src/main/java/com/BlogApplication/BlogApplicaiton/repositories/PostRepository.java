package com.BlogApplication.BlogApplicaiton.repositories;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.repositories.entity.Post;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByCategory(Category category);
    List<Post> findAllByUser(User user);

    List<Post> findByTitleContaining(String search);
}
