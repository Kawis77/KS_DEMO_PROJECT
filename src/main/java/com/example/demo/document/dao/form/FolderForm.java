package com.example.demo.document.dao.form;

import com.example.demo.document.dao.entity.DocumentEntity;
import com.example.demo.document.dao.entity.FolderEntity;
import com.example.demo.menucomponent.dao.entity.MenuDocumentComponentEntity;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class FolderForm {
    private Long id;
    private String name;
    private String description;
    private Long parent;
    private List<FolderEntity> child;
    private MenuDocumentComponentEntity parentComponent;
    private List<DocumentEntity> documentEntityList;


    public FolderForm() {
    }
}
