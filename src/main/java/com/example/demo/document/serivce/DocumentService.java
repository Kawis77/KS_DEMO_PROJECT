package com.example.demo.document.serivce;
import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.dao.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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


    public List<DocumentEntity> readAll(){
       return lDocumentRepository.findAll();
    }

    public void delete(Long aId) {

        Optional<DocumentEntity> lOptionalDocumentEntity = lDocumentRepository.findById(aId);

        if (lOptionalDocumentEntity.isPresent()) {
            DocumentEntity lDocumentEntity = lOptionalDocumentEntity.get();
            lDocumentRepository.delete(lDocumentEntity);
        } else {

        }

    }

    public Date convertToSQLDate(String date){
        Date sqlDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = null;
            utilDate = dateFormat.parse(date);
            sqlDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return sqlDate;
    }


}





