package com.example.Coursework.controller;


import com.example.Coursework.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SendMailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/feedback")
    public void receiveFeedback() {
        emailService.sendSimpleMessage("skywokergames@gmail.com", "TEST", "TEXT");
    }
}
