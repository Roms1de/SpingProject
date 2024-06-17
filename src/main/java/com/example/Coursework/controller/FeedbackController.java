package com.example.Coursework.controller;

import com.example.Coursework.dto.FeedbackDto;
import com.example.Coursework.service.AddDBService;
import com.example.Coursework.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private AddDBService addDBService;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/feedback")
    public ResponseEntity<String> receiveFeedback(@RequestBody FeedbackDto feedbackDto) {
        System.out.println("Received Feedback: " + feedbackDto);

        boolean isAddedToDB = addDBService.addDB(feedbackDto);
        if (isAddedToDB) {
            try {
                // Формируем тело письма с информацией от пользователя
                String subject = "New Feedback Received";
                String body = "Name: " + feedbackDto.getName() + "\nEmail: " + feedbackDto.getEmail() + "\nMessage: " + feedbackDto.getMessage();
                emailSenderService.sendEmail("your_email@example.com", subject, body);

                return ResponseEntity.ok("Feedback received successfully");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error sending email: " + e.getMessage());
                return ResponseEntity.status(500).body("Error sending email");
            }
        } else {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
