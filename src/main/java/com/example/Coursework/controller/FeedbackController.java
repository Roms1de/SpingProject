package com.example.Coursework.controller;

import com.example.Coursework.dto.FeedbackDto;
import com.example.Coursework.entity.FeedbackEntity;
import com.example.Coursework.repository.FeedbackRepository;
import com.example.Coursework.service.AddDBService;
import com.example.Coursework.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private AddDBService addDBService;

    @Autowired
    private FeedbackRepository feedbackRepository;

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
                String body = String.format(
                        "Dear Admin,\n\n" +
                                "You have received a new feedback message from your website.\n\n" +
                                "Details of the feedback are as follows:\n\n" +
                                "Name: %s\n" +
                                "Email: %s\n" +
                                "Message:\n%s\n\n" +
                                "Best Regards,\n" +
                                "Your Website Team",
                        feedbackDto.getName(),
                        feedbackDto.getEmail(),
                        feedbackDto.getMessage()
                );
                emailSenderService.sendEmail("romantsy21@gmail.com", subject, body);

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
