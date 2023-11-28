package com.example.demo.document.web;

import com.example.demo.document.dao.entity.FolderEntity;
import com.example.demo.document.dao.form.FolderForm;
import com.example.demo.document.serivce.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/document")
public class FolderController {


    @Autowired
    FolderService folderService;

    @PostMapping("/folder/create")
    public ResponseEntity<Object> createFolder(@RequestParam FolderForm form) {
        if (form != null) {
            FolderEntity folderEntity = new FolderEntity();
            folderEntity.setName(form.getName());
            folderEntity.setDescription(form.getDescription());
            folderEntity.setParent(form.getParent());
            folderService.create(folderEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
