package com.example.demo.document.dao.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegularDocumentDataForm {

    private Long id ;
    private Long folderId;
    private String title;
    private String owner;
    private String createDate;
    private String location;
    private String category;
    private String version;
    private String publicationNote;
    private String content;
}

