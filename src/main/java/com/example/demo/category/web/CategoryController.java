package com.example.demo.category.web;

import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.category.service.CategoryService;
import com.example.demo.users.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/category/")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/read/all")
    public ResponseEntity<List<CategoryEntity>> getAllUsers(){

        List<CategoryEntity> categoryEntityList = categoryService.readAll();
        if (categoryEntityList != null) {
            return new ResponseEntity<>(categoryEntityList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
