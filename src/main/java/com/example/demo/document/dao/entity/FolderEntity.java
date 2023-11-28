package com.example.demo.document.dao.entity;

import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(name = "parent_id")
    private Long parent;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FolderEntity> child;
    @ManyToOne
    private MenuDocumentComponentEntity parentComponent;
    @OneToMany
    private List<DocumentEntity> documentEntityList;

    public FolderEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }


    public MenuDocumentComponentEntity getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(MenuDocumentComponentEntity parentComponent) {
        this.parentComponent = parentComponent;
    }

    public List<DocumentEntity> getDocumentEntityList() {
        return documentEntityList;
    }

    public void setDocumentEntityList(List<DocumentEntity> documentEntityList) {
        this.documentEntityList = documentEntityList;
    }
}
