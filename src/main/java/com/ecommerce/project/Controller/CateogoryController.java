package com.ecommerce.project.Controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
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
  public ResponseEntity<List<Category>> getAllCategories(){
      List<Category> categories = categoryService.getAllCategories();
      return new ResponseEntity<>(categories,HttpStatus.OK);
  }

  @PostMapping("/public/categories")
  public ResponseEntity<String> createCategory(@RequestBody Category category){
      categoryService.createCategory(category);
      return new ResponseEntity<>("Category Added Successfully",HttpStatus.CREATED);
  }

  @DeleteMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
      try {
          String status = categoryService.deleteCategory(categoryId);
          //return new ResponseEntity<>(status, HttpStatus.OK);
          //return ResponseEntity.status(HttpStatus.OK).body(status);
          return ResponseEntity.ok(status);

      } catch (ResponseStatusException e) {
          return new ResponseEntity<>(e.getReason(),e.getStatusCode());
      }
  }

  @PatchMapping("/admin/categories/{categoryId}")
  public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                               @PathVariable Long categoryId){
      try {
          Category updateCategory = categoryService.updateCategory(category,categoryId);
          return new ResponseEntity<>("Udated Successfully", HttpStatus.OK);

      } catch (ResponseStatusException e) {
          return new ResponseEntity<>(e.getReason(),e.getStatusCode());
      }
  }



}
