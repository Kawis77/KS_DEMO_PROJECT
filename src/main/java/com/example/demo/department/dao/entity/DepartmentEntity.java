package com.example.demo.department.dao.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class DepartmentEntity {
    @Id
    @GeneratedValue
    private Long id;


}
