package com.example.demo.document.dao.entity;

import com.example.demo.category.dao.entity.CategoryEntity;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import com.example.demo.users.dao.entity.UserEntity;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;


    public DocumentEntity() {
    }

    public DocumentEntity(Long id, String path, String title, String size, Integer version, Date createDate, Integer type, String publicationNote, MenuDocumentComponentEntity location, CategoryEntity category, UserEntity owner ) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.size = size;
        this.version = version;
        this.createDate = createDate;
        this.type = type;
        this.publicationNote = publicationNote;
        this.location = location;
        this.categoryEntity = category;
        this.owner = owner;

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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
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

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

}
