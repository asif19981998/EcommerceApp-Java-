package com.ecommerce.project.Controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CateogoryController {

    @Autowired
    private CategoryService categoryService;

  @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
  public ResponseEntity<CategoryResponse> getAllCategories(
          @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
          @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
          @RequestParam(name = "sortBy",defaultValue =AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
          @RequestParam(name = "sortOrder",defaultValue =AppConstants.SORT_DIR,required = false) String sortOrder
  ){
      CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
      return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
  }

  @PostMapping("/public/categories")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
      CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
      return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
          CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
          return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
  }

  @PatchMapping("/admin/categories/{categoryId}")
  public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                               @PathVariable Long categoryId){
          CategoryDTO updateCategory = categoryService.updateCategory(categoryDTO,categoryId);
          return new ResponseEntity<>(updateCategory, HttpStatus.OK);
  }

}
