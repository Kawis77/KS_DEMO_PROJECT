package com.example.demo.employer.dao.repository;

import com.example.demo.employer.dao.entity.EmployerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {
}
