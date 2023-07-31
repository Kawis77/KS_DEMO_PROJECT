package com.example.demo.category.service;

import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.category.dao.repository.CategoryRepository;
import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.users.dao.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;



    public CategoryEntity create(CategoryEntity categoryEntity){

        return categoryRepository.save(categoryEntity);
    }


    public List<CategoryEntity> readAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList;
    }

    public void delete(Long aId) {

        Optional<CategoryEntity> lOptionalCategoryEntity = categoryRepository.findById(aId);

        if (lOptionalCategoryEntity.isPresent()) {
            CategoryEntity lCategoryEntity = lOptionalCategoryEntity.get();
            categoryRepository.delete(lCategoryEntity);
        } else {
            log.error("Category with this id is nor present");
            Optional.empty();
        }

    }

    public CategoryEntity getCategoryById(Long id) {

            Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(id);
            if (categoryEntityOptional.isPresent()) {
                return categoryEntityOptional.get();
            }
            log.error("Category for this id does not exist");
            return null;
    }

}
