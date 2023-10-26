package ir.snapp.example.service;

import ir.snapp.example.model.Feedback;
import ir.snapp.example.model.dto.CreateFeedbackRequest;
import ir.snapp.example.model.dto.FeedbackPageRequest;
import ir.snapp.example.model.dto.request.BikerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FeedbackService
{
    Feedback create(CreateFeedbackRequest createFeedbackRequest);

    Page<Feedback> getAllOrderByDeliveryDate(FeedbackPageRequest feedbackPageRequest);
    Page<Feedback> getBikerFeedback(Pageable pageable, Long bikerId);

}
