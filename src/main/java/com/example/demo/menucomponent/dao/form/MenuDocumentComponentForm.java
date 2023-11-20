package com.example.demo.menucomponent.dao.form;

import com.example.demo.document.dao.entity.DocumentEntity;

import java.util.List;

public class MenuDocumentComponentForm {

    Long id;
    private String name;

    private String description;

    private List<DocumentEntity> documents;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
