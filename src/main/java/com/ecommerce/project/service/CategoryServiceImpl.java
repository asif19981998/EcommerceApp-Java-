package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {

//        Long lastItemCategory = 0L;
        List<Category> categories = categoryRepository.findAll();

//        if(!categories.isEmpty()){
//            lastItemCategory = categories.getLast().getCategoryId();
//        }
//        category.setCategoryId(lastItemCategory+1);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        Category existingCategory = category.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepository.delete(existingCategory);

        return "Category has been deleted";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {
        Category savedCategory = categoryRepository.
                findById(categoryId).orElseThrow(()->
                        new ResourceNotFoundException("Category","CategoryId",categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);

        return  savedCategory;
    }
}
