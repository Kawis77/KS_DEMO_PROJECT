package com.example.demo.document.serivce;

import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.dao.repository.DocumentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.example.demo.document.constans.DocumentConstants.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DocumentService {


    private DocumentRepository lDocumentRepository;

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
            Optional.empty();
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

    public String getContentDocumentType (String name){
        String contentType = null;
        if (name.contains(DOCUMENT_EXTENSION_PDF)) {
            contentType = DOCUMENT_CONTENT_PDF ;
        } else if (name.contains(DOCUMENT_EXTENSION_JPEG)) {
            contentType = DOCUMENT_CONTENT_JPEG;
        } else if (name.contains(DOCUMENT_EXTENSION_PNG)) {
            contentType = DOCUMENT_CONTENT_PNG;
        } else if (name.contains(DOCUMENT_EXTENSION_DOCX)) {
            contentType =DOCUMENT_CONTENT_DOCX;
        } else if (name.contains(DOCUMENT_EXTENSION_XLSX)) {
            contentType = DOCUMENT_CONTENT_XLSX;
        } else if (name.contains(DOCUMENT_EXTENSION_CSV)) {
            contentType = DOCUMENT_CONTENT_CSV;
        }else if (name.contains(DOCUMENT_EXTENSION_TXT)){
            contentType = DOCUMENT_CONTENT_TXT;
        }

        return contentType;
    }
}





