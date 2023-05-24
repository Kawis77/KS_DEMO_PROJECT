package com.example.demo.menucomponent.dao.repository;

import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDocumentComponentRepository extends JpaRepository<MenuDocumentComponentEntity , Long> {
}
