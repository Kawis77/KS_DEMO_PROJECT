package com.example.demo.document.web;

import com.aspose.words.HtmlSaveOptions;
import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.category.service.CategoryService;
import com.example.demo.document.dao.form.ExternalDocumentDataForm;
import com.example.demo.document.dao.form.RegularDocumentDataForm;
import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.serivce.DocumentService;
import com.example.demo.document.serivce.ExternalDocumentService;
import com.example.demo.document.serivce.RegularDocumentService;
import com.example.demo.files.dao.FileConstants;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.service.MenuDocumentComponentService;
import com.example.demo.users.dao.entity.UserEntity;
import com.example.demo.users.service.UserService;
import com.example.demo.validation.ValidationError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.document.constans.DocumentConstants.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        //validate fields
        List<String> lListError = documentService.validateDocumentFields(documentData);
        if (lListError.isEmpty()) {
            File file = regularDocumentService.createRegularDocumentContent(documentData);
            DocumentEntity document = new DocumentEntity();
            document.setTitle(documentData.getTitle());
            UserEntity userEntity = userService.getUserById(Long.parseLong(documentData.getOwner())).get();
            if (userEntity != null) {
                document.setOwner(userEntity);
            }
            document.setContent(documentData.getContent());
            MenuDocumentComponentEntity menuDocumentComponentEntity = menuDocumentComponentService.getMenuComponentById(Long.parseLong(documentData.getLocation()));
            if (menuDocumentComponentEntity != null) {
                document.setLocation(menuDocumentComponentEntity);
            }
            CategoryEntity categoryEntity = categoryService.getCategoryById(Long.parseLong(documentData.getCategory()));
            if (categoryEntity != null) {
                document.setCategoryEntity(categoryEntity);
            }
            document.setPath(file.getName());
            document.setCreateDate(documentService.convertToUtilDate(documentData.getCreateDate()));
            document.setVersion(Integer.parseInt(documentData.getVersion()));
            document.setPublicationNote(documentData.getPublicationNote());
            document.setType(REGULAR_DOC_DOCUMENT);

            documentService.create(document);
            // return 200 when is ok
            log.debug("document with id : " + document.getId() + " successfully created");
            documentData.setId(document.getId());
            return new ResponseEntity<>(documentData , HttpStatus.OK);
        }
        log.error("Failed to update this object :" + documentData.getId());
        return new ResponseEntity<>(new ValidationError(lListError), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/external/create")
    public ResponseEntity<Object> createExternalDocument(@ModelAttribute ExternalDocumentDataForm documentData) {
        //validate fields
        List<String> lListError = documentService.validateDocumentFields(documentData);

        if (lListError.isEmpty()) {
            DocumentEntity document = new DocumentEntity();
            File file = externalDocumentService.createExternalFile(documentData.getDocumentFile());
            document.setPath(file.getName());
            document.setTitle(documentData.getTitle());
            UserEntity userEntity = userService.getUserById(Long.parseLong(documentData.getOwner())).get();
            if (userEntity != null) {
                document.setOwner(userEntity);
            }
            MenuDocumentComponentEntity menuDocumentComponentEntity = menuDocumentComponentService.getMenuComponentById(Long.parseLong(documentData.getLocation()));
            if (menuDocumentComponentEntity != null) {
                document.setLocation(menuDocumentComponentEntity);
            }
            CategoryEntity categoryEntity = categoryService.getCategoryById(Long.parseLong(documentData.getCategory()));
            if (categoryEntity != null) {
                document.setCategoryEntity(categoryEntity);
            }
            document.setCreateDate(documentService.convertToUtilDate(documentData.getCreateDate()));
            document.setVersion(Integer.parseInt(documentData.getVersion()));
            document.setPublicationNote(documentData.getPublicationNote());
            document.setType(EXTERNAL_DOC_DOCUMENT);

            documentService.create(document);
            // return 200 when is ok
            log.debug("document with id : " + document.getId() + " successfully created");
            documentData.setId(document.getId());
            return new ResponseEntity<>(documentData , HttpStatus.OK);

        }
        log.error("Failed to update this object :" + documentData.getId());
        return new ResponseEntity<>(new ValidationError(lListError), HttpStatus.BAD_REQUEST);
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

    @GetMapping("/read/one/doc/{id}")
    public ResponseEntity<DocumentEntity> getOneDocument(@PathVariable("id") Long id) {
        if (id != null) {
            DocumentEntity documentEntity = documentService.findDocumentById(id);
            if (documentEntity != null) {
                return new ResponseEntity<>(documentEntity, HttpStatus.OK);
            }
        }
        log.error("Id for DocumentEntity is null");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/show/one/{name}")
    public ResponseEntity<byte[]> showDocumentInFrame(@PathVariable("name") String name) throws IOException {
        String path = null;
        //check type of file
        if (name != null && !name.isEmpty()) {
            File externalDocument = new File(EXTERNAL_DOCUMENT_PATH + name);
            File regularDocument = new File(REGULAR_DOCUMENT_PATH + name);
            File regularFile = new File(FileConstants.REGULAR_FILE_PATH + name);
            if (externalDocument.exists()) {
                path = externalDocument.getPath();
            } else if (regularDocument.exists()) {
                path = regularDocument.getPath();
            } else if (regularFile.exists()) {
                path = regularFile.getPath();
            }
            //convert file to bytes
            if (path != null && !path.isEmpty()) {
                byte[] fileBytes = Files.readAllBytes(Paths.get(path));
                //check content of document
                String contentType = documentService.getContentDocumentType(name);
                if (contentType != null) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(fileBytes);

                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/external/update")
    public ResponseEntity<Object> updateExternalDocument(@ModelAttribute ExternalDocumentDataForm documentData) {
        //validate fields
        List<String> lListError = documentService.validateDocumentFields(documentData);

        if (documentData != null && lListError.isEmpty()) {
            DocumentEntity documentEntity = documentService.findDocumentById(documentData.getId());
            if (documentEntity != null) {
                File file = externalDocumentService.createExternalFile(documentData.getDocumentFile());
                if (file != null) {
                    documentEntity.setPath(file.getName());
                }
            }
            documentEntity.setTitle(documentData.getTitle());
            documentEntity.setVersion(Integer.parseInt(documentData.getVersion()));
            documentEntity.setPublicationNote(documentData.getPublicationNote());
            documentEntity.setCreateDate(documentService.convertToUtilDate(documentData.getCreateDate()));
            UserEntity userEntity = userService.getUserById(Long.parseLong(documentData.getOwner())).get();
            if (userEntity != null) {
                documentEntity.setOwner(userEntity);
            }
            MenuDocumentComponentEntity menuDocumentComponentEntity = menuDocumentComponentService.getMenuComponentById(Long.parseLong(documentData.getLocation()));
            if (menuDocumentComponentEntity != null) {
                documentEntity.setLocation(menuDocumentComponentEntity);
            }
            CategoryEntity categoryEntity = categoryService.getCategoryById(Long.parseLong(documentData.getCategory()));
            if (categoryEntity != null) {
                documentEntity.setCategoryEntity(categoryEntity);
            }
            documentService.update(documentEntity);
            log.debug("Document updated correctly with id: " + documentEntity.getId());
            return ResponseEntity.ok().build();
        }
        log.error("Failed to update this object :" + documentData.getId());
        return new ResponseEntity<>(new ValidationError(lListError), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/regular/update")
    public ResponseEntity<Object> updateRegularDocument(@ModelAttribute RegularDocumentDataForm regularDocumentDataForm) {
        List<String> lListError = documentService.validateDocumentFields(regularDocumentDataForm);
        if (regularDocumentDataForm != null && lListError.isEmpty()) {
            DocumentEntity documentEntity = documentService.findDocumentById(regularDocumentDataForm.getId());
            if (documentEntity != null) {
                File file = regularDocumentService.createRegularDocumentContent(regularDocumentDataForm);
                if (file.exists()) {
                    documentEntity.setPath(file.getName());
                }
                documentEntity.setTitle(regularDocumentDataForm.getTitle());
                documentEntity.setVersion(Integer.parseInt(regularDocumentDataForm.getVersion()));
                documentEntity.setPublicationNote(regularDocumentDataForm.getPublicationNote());
                documentEntity.setCreateDate(documentService.convertToUtilDate(regularDocumentDataForm.getCreateDate()));
                documentEntity.setContent(regularDocumentDataForm.getContent());
                UserEntity userEntity = userService.getUserById(Long.parseLong(regularDocumentDataForm.getOwner())).get();
                if (userEntity != null) {
                    documentEntity.setOwner(userEntity);
                }
                MenuDocumentComponentEntity menuDocumentComponentEntity = menuDocumentComponentService.getMenuComponentById(Long.parseLong(regularDocumentDataForm.getLocation()));
                if (menuDocumentComponentEntity != null) {
                    documentEntity.setLocation(menuDocumentComponentEntity);
                }
                CategoryEntity categoryEntity = categoryService.getCategoryById(Long.parseLong(regularDocumentDataForm.getCategory()));
                if (categoryEntity != null) {
                    documentEntity.setCategoryEntity(categoryEntity);
                }
                documentService.update(documentEntity);
                log.debug("Document updated correctly with id: " + documentEntity.getId());
                return ResponseEntity.ok().build();
            }
        }
        log.error("Failed to update this object" + regularDocumentDataForm.getId());
        return new ResponseEntity<>(new ValidationError(lListError), HttpStatus.BAD_REQUEST);
    }

}
