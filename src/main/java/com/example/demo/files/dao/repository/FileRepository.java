package com.example.demo.files.dao.repository;

import com.example.demo.files.dao.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<FileEntity , Long> {
}
