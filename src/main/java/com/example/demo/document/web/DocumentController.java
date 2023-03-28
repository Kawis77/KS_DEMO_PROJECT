package com.example.demo.document.web;

import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.serivce.DocumentService;
import com.example.demo.document.serivce.RegularDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RequestMapping("/api")
@RestController
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    @Autowired

    private final RegularDocumentService regularDocumentService;

    public DocumentController(DocumentService documentService, RegularDocumentService regularDocumentService) {
        this.documentService = documentService;
        this.regularDocumentService = regularDocumentService;
    }

    @PostMapping("/document/create")
    public ResponseEntity<Object> createRegularDocument(@RequestBody RegularDocumentDataForm documentData) {

        File file = regularDocumentService.createRegularDocumentContent(documentData);

        DocumentEntity document = new DocumentEntity();
        document.setTitle(documentData.getTitle());
        document.setLocation(documentData.getLocation());
        document.setAuthor(null);
        if (file != null) {
            document.setPath(file.getPath());
        }
        if (documentData.getCreateDate() != null ){
            document.setCreateDate(documentService.convertToSQLDate(documentData.getCreateDate()));
        }
        document.setPublicationNote(documentData.getPublicationNote());

        documentService.create(document);

        // return 200 when is ok
        return ResponseEntity.ok().build();
    }



}
