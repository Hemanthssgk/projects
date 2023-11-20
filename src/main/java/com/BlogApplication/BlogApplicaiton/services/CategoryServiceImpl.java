package com.BlogApplication.BlogApplicaiton.services;

import com.BlogApplication.BlogApplicaiton.exceptions.NoResourceFoundException;
import com.BlogApplication.BlogApplicaiton.repositories.CategoryRepo;
import com.BlogApplication.BlogApplicaiton.repositories.entity.Category;
import com.BlogApplication.BlogApplicaiton.services.interfaces.CategoryService;
import com.BlogApplication.BlogApplicaiton.services.model.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(Category category) {
        if (category!=null)
        {
            categoryRepo.save(category);
            return modelMapper.map(category, CategoryDTO.class);
        }
        throw new NoResourceFoundException("Category","field is null",null);

    }

    @Override
    public CategoryDTO updateCategory(Category category, Integer id) {
        if (id!=null&&category!=null)
        {
            Category category1 =  categoryRepo.findById(id).orElseThrow(() -> new NoResourceFoundException("Category","id",id));
            category.setCategoryId(id);
            category.setPosts(category1.getPosts());
            category.setDescription(category1.getDescription());
            category.setTitle(category1.getTitle());
            return this.modelMapper.map(categoryRepo.save(category), CategoryDTO.class);
        }

        throw new NoResourceFoundException("Category","id or Category is null",id);
    }

    @Override
    public void deleteCategory(Integer id) {
        if (id!=null&&categoryRepo.existsById(id))
        {
        categoryRepo.deleteById(id);
        return;
        }

        throw new NoResourceFoundException("Category","id",id);
    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        if (id!=null&& categoryRepo.existsById(id))
        {
            return modelMapper.map(categoryRepo.findById(id), CategoryDTO.class);
        }
        throw new NoResourceFoundException("Category","id",id);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepo.findAll().stream().map(category -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }
}
