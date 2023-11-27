package com.BlogApplication.BlogApplicaiton.rest.repositories;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Post;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByCategory(Category category);
    List<Post> findAllByUser(User user);

    List<Post> findByTitleContaining(String search);
}
