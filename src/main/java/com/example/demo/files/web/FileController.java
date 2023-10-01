package com.example.demo.files.web;

import com.example.demo.files.dao.entity.FileEntity;
import com.example.demo.files.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RequestMapping("/api/file")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/save")
    public ResponseEntity<String> saveFile(@RequestParam MultipartFile file) {
        if (file != null) {
            String name = fileService.saveFileOnServer(file);
            if (name != null && !name.isEmpty()) {

                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(name);
                fileService.saveFile(fileEntity);

                return new ResponseEntity<>(name, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
