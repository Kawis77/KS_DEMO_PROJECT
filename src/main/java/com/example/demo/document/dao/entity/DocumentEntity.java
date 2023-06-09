package com.example.demo.document.dao.entity;


import com.example.demo.employer.dao.entity.EmployerEntity;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
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

    private Integer type;

    private String publicationNote;

    @ManyToOne
    @JoinColumn(name = "menu_component_id")
    private MenuDocumentComponentEntity location;

    private String category;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployerEntity author;


    public DocumentEntity() {
    }

    public DocumentEntity(Long id, String path, String title, String size, Integer version, Date createDate, Integer type, String publicationNote, MenuDocumentComponentEntity location, String category, EmployerEntity author) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.size = size;
        this.version = version;
        this.createDate = createDate;
        this.type = type;
        this.publicationNote = publicationNote;
        this.location = location;
        this.category = category;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublicationNote() {
        return publicationNote;
    }

    public void setPublicationNote(String publicationNote) {
        this.publicationNote = publicationNote;
    }

    public MenuDocumentComponentEntity getLocation() {
        return location;
    }

    public void setLocation(MenuDocumentComponentEntity location) {
        this.location = location;
    }

    public EmployerEntity getAuthor() {
        return author;
    }

    public void setAuthor(EmployerEntity author) {
        this.author = author;
    }
}
