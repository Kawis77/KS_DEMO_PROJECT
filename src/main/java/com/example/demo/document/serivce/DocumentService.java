package com.example.demo.document.serivce;


import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.dao.repository.DocumentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DocumentService {


    private DocumentRepository lDocumentRepository;

    public DocumentService(DocumentRepository lDocumentRepository) {
        this.lDocumentRepository = lDocumentRepository;
    }


    public DocumentEntity create(DocumentEntity aDocumentEntity) {

        return lDocumentRepository.save(aDocumentEntity);
    }

    public DocumentEntity edit(DocumentEntity aDocumentEntity) {

        return lDocumentRepository.save(aDocumentEntity);
    }

    public void delete(Long aId) {

        Optional<DocumentEntity> lOptionalDocumentEntity = lDocumentRepository.findById(aId);

        if (lOptionalDocumentEntity.isPresent()) {
            DocumentEntity lDocumentEntity = lOptionalDocumentEntity.get();
            lDocumentRepository.delete(lDocumentEntity);
        } else {
            throw new EntityNotFoundException("Document not found");
        }

    }

}





