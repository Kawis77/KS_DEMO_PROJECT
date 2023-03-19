package com.example.demo.document.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DocumentController {


    @PostMapping("/document/create")
    public String create() {
        System.out.println("sdefswefwefwe");
        return "hello";
    }
}
