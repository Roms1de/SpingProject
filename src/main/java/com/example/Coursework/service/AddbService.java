package com.example.Coursework.service;


import com.example.Coursework.dto.FeedbackDto;
import com.example.Coursework.entity.FeedbackEntity;
import com.example.Coursework.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddbService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Boolean addBd(FeedbackDto feedbackDto) {
        if (feedbackDto.getName() == null || feedbackDto.getEmail() == null || feedbackDto.getMessage() == null) {
            return ResponseEntity.badRequest().body("Missing required fields").hasBody();
        }

        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setName(feedbackDto.getName());
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setMessage(feedbackDto.getMessage());

        feedbackRepository.save(feedback);
        return true;
    }

    public AddbService() {

    }


}
