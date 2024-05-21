package com.example.Coursework.controller;

import com.example.Coursework.dto.FeedbackDto;
import com.example.Coursework.entity.FeedbackEntity;
import com.example.Coursework.repository.FeedbackRepository;
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
    private FeedbackRepository feedbackRepository;

    @PostMapping("/feedback")
    public ResponseEntity<String> receiveFeedback(@RequestBody FeedbackDto feedbackDto) {
        if (feedbackDto.getName() == null || feedbackDto.getEmail() == null || feedbackDto.getMessage() == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setName(feedbackDto.getName());
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setMessage(feedbackDto.getMessage());

        feedbackRepository.save(feedback);

        return ResponseEntity.ok("Feedback received successfully");
    }
}
