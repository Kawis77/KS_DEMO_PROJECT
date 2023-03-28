package com.example.demo.document.dao.entity;


import com.example.demo.employer.dao.entity.EmployerEntity;
import jakarta.persistence.*;

import java.io.File;
import java.util.Date;

@Entity
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    private String title;

    private String size;

    private Integer version;
    private Date createDate;

    private String type;

    private String publicationNote;

    private String location;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployerEntity author;


    public DocumentEntity() {
    }

    public DocumentEntity(Long id, String path, String title, String size, Integer version, Date createDate, String type, String publicationNote, String location, File file, EmployerEntity author) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.size = size;
        this.version = version;
        this.createDate = createDate;
        this.type = type;
        this.publicationNote = publicationNote;
        this.location = location;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublicationNote() {
        return publicationNote;
    }

    public void setPublicationNote(String publicationNote) {
        this.publicationNote = publicationNote;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EmployerEntity getAuthor() {
        return author;
    }

    public void setAuthor(EmployerEntity author) {
        this.author = author;
    }
}
