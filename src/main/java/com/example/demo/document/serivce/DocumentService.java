package com.example.demo.document.serivce;

import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.dao.form.ExternalDocumentDataForm;
import com.example.demo.document.dao.form.RegularDocumentDataForm;
import com.example.demo.document.dao.repository.DocumentRepository;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.menucomponent.service.MenuDocumentComponentService;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import static com.example.demo.document.constans.DocumentConstants.*;

import java.awt.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Log4j2
@Service
public class DocumentService {


    @Autowired
    private DocumentRepository lDocumentRepository;

    @Autowired
    private MenuDocumentComponentService componentService;

    public DocumentService(DocumentRepository lDocumentRepository) {
        this.lDocumentRepository = lDocumentRepository;
    }


    public DocumentEntity create(DocumentEntity aDocumentEntity) {

        return lDocumentRepository.save(aDocumentEntity);
    }

    public DocumentEntity update(DocumentEntity aDocumentEntity) {

        return lDocumentRepository.save(aDocumentEntity);
    }


    public List<DocumentEntity> readAll() {
        return lDocumentRepository.findAll();
    }

    public void delete(Long aId) {
        Optional<DocumentEntity> lOptionalDocumentEntity = lDocumentRepository.findById(aId);
        if (lOptionalDocumentEntity.isPresent()) {
            DocumentEntity lDocumentEntity = lOptionalDocumentEntity.get();
            lDocumentRepository.delete(lDocumentEntity);
        } else {
            log.error("Document with this id is nor present");
        }

    }

    public Date convertToUtilDate(String date) {
        java.util.Date utilDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return utilDate;
    }

    public List<DocumentEntity> getAllWithMenuId(Long id) {

        List<DocumentEntity> menuDocuments = new ArrayList<>();
        List<DocumentEntity> allDocuments = lDocumentRepository.findAll();
        for (DocumentEntity document : allDocuments) {
            if (document.getLocation().getId() == id) {
                menuDocuments.add(document);
            }
        }
        if (menuDocuments.size() > 0) {
            return menuDocuments;
        } else {
            log.error("Documents list with this menu id is empty ");
            return menuDocuments;
        }
    }

    public DocumentEntity findDocumentById(Long id) {
        if (id != null) {
            Optional<DocumentEntity> optionalDocumentEntity = lDocumentRepository.findById(id);
            if (optionalDocumentEntity.isPresent()) {
                return optionalDocumentEntity.get();
            } else {
                log.error("Document with this id " + id + " not exist in system");
                return null;
            }
        }
        log.error("Id for DocumentEntity is null");
        return null;
    }

    public String getContentDocumentType(String name) {
        String contentType = null;
        if (name.contains(DOCUMENT_EXTENSION_PDF)) {
            contentType = DOCUMENT_CONTENT_PDF;
        } else if (name.contains(DOCUMENT_EXTENSION_JPEG)) {
            contentType = DOCUMENT_CONTENT_JPEG;
        } else if (name.contains(DOCUMENT_EXTENSION_PNG)) {
            contentType = DOCUMENT_CONTENT_PNG;
        } else if (name.contains(DOCUMENT_EXTENSION_DOCX)) {
            contentType = DOCUMENT_CONTENT_DOCX;
        } else if (name.contains(DOCUMENT_EXTENSION_XLSX)) {
            contentType = DOCUMENT_CONTENT_XLSX;
        } else if (name.contains(DOCUMENT_EXTENSION_CSV)) {
            contentType = DOCUMENT_CONTENT_CSV;
        } else if (name.contains(DOCUMENT_EXTENSION_TXT)) {
            contentType = DOCUMENT_CONTENT_TXT;
        }

        return contentType;
    }


    public List<String> validateDocumentFields(Object documentForm) {
        List<String> validationList = new ArrayList<>();

        try {
            if (documentForm instanceof ExternalDocumentDataForm) {
                ExternalDocumentDataForm externalDocumentDataForm = (ExternalDocumentDataForm) documentForm;
                Field[] fields = ExternalDocumentDataForm.class.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(externalDocumentDataForm) == null && !field.getName().equals("id")) {
                        validationList.add("Pole o nazwie " + field.getName() + " nie zostało wypełnione");
                    }
                }
            } else if (documentForm instanceof RegularDocumentDataForm) {
                RegularDocumentDataForm regularDocumentDataForm = (RegularDocumentDataForm) documentForm;

                Field[] fields = RegularDocumentDataForm.class.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.get(regularDocumentDataForm) == null && !field.getName().equals("id")) {
                        validationList.add("Pole o nazwie " + field.getName() + " nie zostało wypełnione");
                    }
                }
            }
        } catch (IllegalAccessException accessException) {
            log.error(accessException);
        }
        return validationList;
    }
    public Pair<Map<MenuDocumentComponentEntity, List<DocumentEntity>>,List<MenuDocumentComponentEntity>> getDocumentForMoveOption(Long id) {
        List<Map<MenuDocumentComponentEntity, List<DocumentEntity>>> arrayWithMapLocations = new ArrayList<>();
        Map<MenuDocumentComponentEntity, List<DocumentEntity>> leftDocuments = new HashMap<>();
         List<MenuDocumentComponentEntity> rightDocuments = componentService.readAll();
        DocumentEntity documentEntity = findDocumentById(id);
        List<MenuDocumentComponentEntity> componentEntityList = componentService.readAll();
        if (documentEntity != null) {
            leftDocuments.put(documentEntity.getLocation(), Arrays.asList(documentEntity));
        } else {
            for (MenuDocumentComponentEntity documentComponent : componentEntityList) {
                List<DocumentEntity> documentsFromMenu = getAllWithMenuId(documentComponent.getId());
                leftDocuments.put(documentComponent, documentsFromMenu);
            }
        }

        return Pair.of(leftDocuments, rightDocuments);
    }
}





