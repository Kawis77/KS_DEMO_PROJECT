package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {




    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    @ResponseBody

    public String homeAction(Model model){
         System.out.println("test test");
        return "Success";

    }
}