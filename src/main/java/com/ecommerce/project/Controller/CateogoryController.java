package com.ecommerce.project.Controller;

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
//
//    public CateogoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

  //@GetMapping("/api/public/categories")
  @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
  public ResponseEntity<CategoryResponse> getAllCategories(){
      CategoryResponse categoryResponse = categoryService.getAllCategories();
      return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
  }

  @PostMapping("/public/categories")
  public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
      CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
      return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
          String status = categoryService.deleteCategory(categoryId);
          return new ResponseEntity<>(status, HttpStatus.OK);
  }

  @PatchMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,
                                               @PathVariable Long categoryId){
        Category updateCategory = categoryService.updateCategory(category,categoryId);
          return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
  }

}
