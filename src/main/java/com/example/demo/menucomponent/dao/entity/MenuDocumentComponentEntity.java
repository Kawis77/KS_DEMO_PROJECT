package com.example.demo.menucomponent.dao.entity;

import javax.persistence.*;


@Entity
@Table(name = "MenuDocumentComponent")
public class MenuDocumentComponentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;



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
