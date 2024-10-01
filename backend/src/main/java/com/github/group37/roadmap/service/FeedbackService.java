package com.github.group37.roadmap.service;

import java.util.List;
import com.github.group37.roadmap.percistance.FeedbackRepo;
import com.github.group37.roadmap.percistance.models.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class FeedbackService {

    private final FeedbackRepo feedbackRepository;
    
    @Autowired
    public FeedbackService(FeedbackRepo feedbackRepository) {

        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public Feedback saveFeedback(Feedback feedback) {

        return feedbackRepository.save(feedback);
    }
    
    public List<Feedback> getAllFeedback() {

        return feedbackRepository.findAll();
    }
}
