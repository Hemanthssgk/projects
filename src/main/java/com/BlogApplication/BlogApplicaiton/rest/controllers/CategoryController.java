package com.BlogApplication.BlogApplicaiton.rest.controllers;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.CategoryService;
import com.BlogApplication.BlogApplicaiton.rest.services.model.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category)
    {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody Category category, @PathVariable Integer id)
    {
        return ResponseEntity.ok(categoryService.updateCategory(category,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Deleted:"+id,new Date(),true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id)
    {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategories()
    {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
