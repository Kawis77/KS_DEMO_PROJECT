package com.example.demo.function.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class FunctionEntity {
    @Id
    @GeneratedValue
    private Long id;


}
