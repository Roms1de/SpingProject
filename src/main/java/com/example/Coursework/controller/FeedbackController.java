package com.example.Coursework.controller;

import com.example.Coursework.dto.FeedbackDto;
import com.example.Coursework.entity.FeedbackEntity;
import com.example.Coursework.repository.FeedbackRepository;
import com.example.Coursework.service.AddbService;
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
    private AddbService addbService;

    @PostMapping("/feedback")
    public ResponseEntity<String> receiveFeedback(@RequestBody FeedbackDto feedbackDto) {
        if (addbService.addBd(feedbackDto)) {
            return ResponseEntity.ok("Feedback received successfully");
        } else {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
