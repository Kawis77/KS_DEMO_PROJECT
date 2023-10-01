package com.example.demo.document.dao.form;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


public class ExternalDocumentDataForm {

    private Long id;
    private String title;
    private String owner;
    private String author;
    private String createDate;
    private String location;
    private String category;
    private String version;
    private String publicationNote;
    private MultipartFile documentFile;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublicationNote() {
        return publicationNote;
    }

    public void setPublicationNote(String publicationNote) {
        this.publicationNote = publicationNote;
    }


    public MultipartFile getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(MultipartFile documentFile) {
        this.documentFile = documentFile;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



}
