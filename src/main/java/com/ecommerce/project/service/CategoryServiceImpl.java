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

        Category existingCategory = category.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resouce Not Found"));

        categoryRepository.delete(existingCategory);

        return "Category has been deleted";


//        List<Category> categories = categoryRepository.findAll();
//
//        Category category = categories.stream()
//                .filter(c-> c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
//
//        if(category == null){
//            return "category not found";
//        }
//
//        //categories.remove(category);
//        categoryRepository.delete(category);
//
//        return "Category with categoryId:" + categoryId + "has been deleted";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {

        Optional<Category> existingCategory = categoryRepository.findById(categoryId);

        Category savedCategory = existingCategory.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);

        return  savedCategory;
    }
}
