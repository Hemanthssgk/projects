package com.BlogApplication.BlogApplicaiton.repositories;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
