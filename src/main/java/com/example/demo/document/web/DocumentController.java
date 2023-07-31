package com.example.demo.document.web;

import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.category.service.CategoryService;
import com.example.demo.constans.DocumentConstants;
import com.example.demo.document.dao.form.RegularDocumentDataForm;
import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.serivce.DocumentService;
import com.example.demo.document.serivce.RegularDocumentService;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.service.MenuDocumentComponentService;
import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RequestMapping("/api")
@RestController
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    @Autowired
    private final RegularDocumentService regularDocumentService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuDocumentComponentService menuDocumentComponentService;

    @Autowired
    private CategoryService categoryService;


    public DocumentController(DocumentService documentService, RegularDocumentService regularDocumentService) {
        this.documentService = documentService;
        this.regularDocumentService = regularDocumentService;
    }

    @PostMapping("/document/regular/create")
    public ResponseEntity<Object> createRegularDocument(@RequestBody RegularDocumentDataForm documentData) {
        File file = regularDocumentService.createRegularDocumentContent(documentData);

        DocumentEntity document = new DocumentEntity();
        document.setTitle(documentData.getTitle());

        if (documentData.getOwner() != null){
            UserEntity userEntity = userService.getUserById(Long.parseLong(documentData.getOwner())).get();
            if (userEntity != null){
                document.setOwner(userEntity);
            }
        }

        if (documentData.getLocation() != null && !documentData.getLocation().isEmpty()){
            MenuDocumentComponentEntity menuDocumentComponentEntity = menuDocumentComponentService.getMenuComponentById(Long.parseLong(documentData.getLocation()));
            if (menuDocumentComponentEntity != null){
                document.setLocation(menuDocumentComponentEntity);
            }
        }

        if (documentData.getCategory() != null && !documentData.getCategory().isEmpty()){
            CategoryEntity categoryEntity = categoryService.getCategoryById(Long.parseLong(documentData.getCategory()));
            if (categoryEntity != null){
                document.setCategoryEntity(categoryEntity);
            }
        }

        if (file != null) {
            document.setPath(file.getPath());
        }
        if (documentData.getCreateDate() != null && !documentData.getCreateDate().isEmpty()) {
            document.setCreateDate(documentService.convertToSQLDate(documentData.getCreateDate()));
        }

        document.setVersion(document.getVersion());
        document.setPublicationNote(documentData.getPublicationNote());
        document.setType(DocumentConstants.REGULAR_DOC_DOCUMENT);

        documentService.create(document);
        // return 200 when is ok
        return ResponseEntity.ok().build();
    }


    @GetMapping("/document/read/all")
    @ResponseBody
    public ResponseEntity<Object> readAllDocuments() {

        List<DocumentEntity> lList = documentService.readAll();

        if (lList != null) {
            return new ResponseEntity<>(lList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
