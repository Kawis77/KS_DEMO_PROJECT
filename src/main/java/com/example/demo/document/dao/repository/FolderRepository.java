package com.example.demo.document.dao.repository;

import com.example.demo.document.dao.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity , Long> {
}
