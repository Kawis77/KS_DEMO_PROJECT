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

    private File file;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployerEntity author;


}
