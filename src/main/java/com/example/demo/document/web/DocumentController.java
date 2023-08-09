package com.example.demo.document.web;

import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.category.service.CategoryService;
import com.example.demo.constans.DocumentConstants;
import com.example.demo.document.dao.form.ExternalDocumentDataForm;
import com.example.demo.document.dao.form.RegularDocumentDataForm;
import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.serivce.DocumentService;
import com.example.demo.document.serivce.ExternalDocumentService;
import com.example.demo.document.serivce.RegularDocumentService;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.service.MenuDocumentComponentService;
import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Log4j2
@RequestMapping("/api/document")
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
    @Autowired
    private ExternalDocumentService externalDocumentService;


    public DocumentController(DocumentService documentService, RegularDocumentService regularDocumentService) {
        this.documentService = documentService;
        this.regularDocumentService = regularDocumentService;
    }

    @PostMapping("/regular/create")
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


    @PostMapping("/external/create")
    public ResponseEntity<Object> createExternalDocument(@ModelAttribute ExternalDocumentDataForm documentData) {

        DocumentEntity document = new DocumentEntity();

        if (documentData.getDocumentFile() != null && !documentData.getDocumentFile().getName().isEmpty()){
            externalDocumentService.createExternalFile(documentData.getDocumentFile());
        }

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

        if (documentData.getCreateDate() != null && !documentData.getCreateDate().isEmpty()) {
            document.setCreateDate(documentService.convertToSQLDate(documentData.getCreateDate()));
        }
        document.setVersion(document.getVersion());
        document.setPublicationNote(documentData.getPublicationNote());
        document.setType(DocumentConstants.EXTERNAL_DOC_DOCUMENT);

        documentService.create(document);
        // return 200 when is ok
        return ResponseEntity.ok().build();
    }


    @GetMapping("/read/all")
    @ResponseBody
    public ResponseEntity<Object> readAllDocuments() {

        List<DocumentEntity> lList = documentService.readAll();

        if (lList != null) {
            return new ResponseEntity<>(lList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/read/menu/{id}")
    @ResponseBody
    public ResponseEntity<Object> readOneMenuDocuments(@PathVariable Long id) {

        List<DocumentEntity> lList = documentService.getAllWithMenuId(id);

        if (lList != null) {
            return new ResponseEntity<>(lList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/read/one/doc/{wid}")
    public ResponseEntity<DocumentEntity> getOneDocument(@PathVariable("wid") Long id) {
        if (id != null) {

            DocumentEntity documentEntity = documentService.findDocumentById(id);
            if (documentEntity != null) {
                return new ResponseEntity<>(documentEntity, HttpStatus.OK);
            }
        }
        log.error("Id for DocumentEntity is null");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
