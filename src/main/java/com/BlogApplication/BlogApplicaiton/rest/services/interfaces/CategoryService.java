package com.BlogApplication.BlogApplicaiton.rest.services.interfaces;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.rest.services.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(Category category);
    CategoryDTO updateCategory(Category category,Integer id);
    void deleteCategory(Integer id);
    CategoryDTO getCategory(Integer id);
    List<CategoryDTO> getCategories();

}
