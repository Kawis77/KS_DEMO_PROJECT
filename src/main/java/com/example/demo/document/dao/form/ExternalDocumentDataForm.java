package com.example.demo.document.dao.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ExternalDocumentDataForm {

    private Long id;
    private Long folderId;
    private String title;
    private String owner;
    private String createDate;
    private String location;
    private String category;
    private String version;
    private String publicationNote;
    private MultipartFile documentFile;
}
