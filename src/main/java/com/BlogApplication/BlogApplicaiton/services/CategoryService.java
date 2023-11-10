package com.BlogApplication.BlogApplicaiton.services;

import com.BlogApplication.BlogApplicaiton.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.services.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(Category category);
    CategoryDTO updateCategory(Category category,Integer id);
    void deleteCategory(Integer id);
    CategoryDTO getCategory(Integer id);
    List<CategoryDTO> getCategories();

}
