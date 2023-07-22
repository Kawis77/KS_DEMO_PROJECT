package com.example.demo.employer.dao.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployerEntity {
    @Id
    @GeneratedValue
    private Long id;

}
