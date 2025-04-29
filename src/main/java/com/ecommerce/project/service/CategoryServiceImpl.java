package com.ecommerce.project.service;

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

        Long lastItemCategory = 0L;
        List<Category> categories = categoryRepository.findAll();

        if(!categories.isEmpty()){
            lastItemCategory = categories.getLast().getCategoryId();
        }
        category.setCategoryId(lastItemCategory+1);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c-> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        if(category == null){
            return "category not found";
        }

        //categories.remove(category);
        categoryRepository.delete(category);

        return "Category with categoryId:" + categoryId + "has been deleted";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {

        List<Category> categories = categoryRepository.findAll();
        //One way to do update
//        categories.stream()
//                .filter(c-> c.getCategoryId().equals(category.getCategoryId()))
//                .findFirst()
//                .ifPresent(c->c.setCategoryName(category.getCategoryName()));


        Optional<Category> optionalCategory = categories.stream()
                .filter(c-> c.getCategoryId().equals(categoryId))
                .findFirst();
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory;
            savedCategory = categoryRepository.save(existingCategory);
            return savedCategory;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
