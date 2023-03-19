package com.example.demo.employer.dao.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class EmployerEntity {
    @Id
    @GeneratedValue
    private Long id;

}
