package com.example.Coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageController {

    @GetMapping("/index")
    public String showIndex() {
        return "index"; // имя шаблона Thymeleaf без расширения .html
    }
}
