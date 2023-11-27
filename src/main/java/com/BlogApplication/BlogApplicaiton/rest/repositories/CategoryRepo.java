package com.BlogApplication.BlogApplicaiton.rest.repositories;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
