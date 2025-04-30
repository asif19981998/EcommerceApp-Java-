package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty())
            throw new APIException("No category exist !!");

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .collect(Collectors.toList());

        //return new CategoryResponse(categoryDTOS);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        return categoryResponse;

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category savedCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(savedCategory != null)
            throw new APIException("Category with the name " + categoryDTO.getCategoryName() + " already exists !!!");
        Category convertedCategory = modelMapper.map(categoryDTO,Category.class);
        Category newCategory = categoryRepository.save(convertedCategory);

        return modelMapper.map(newCategory,CategoryDTO.class);
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
