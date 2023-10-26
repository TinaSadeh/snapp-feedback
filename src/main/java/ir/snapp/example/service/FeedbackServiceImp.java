package ir.snapp.example.service;

import ir.snapp.example.model.Biker;
import ir.snapp.example.model.Feedback;
import ir.snapp.example.model.dto.CreateFeedbackRequest;
import ir.snapp.example.model.dto.FeedbackPageRequest;
import ir.snapp.example.repository.BikerRepository;
import ir.snapp.example.repository.FeedbackRepository;
import ir.snapp.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Objects;


@Service
@AllArgsConstructor
public class FeedbackServiceImp implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final BikerRepository bikerRepository;
    private final UserRepository userRepository;

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public Feedback create(CreateFeedbackRequest createFeedbackRequest) {
        Biker biker = bikerRepository.findById((long) createFeedbackRequest.getBikerId());
        if (Objects.isNull(biker))
            throw new RuntimeException("Biker with this ID not found : " + createFeedbackRequest.getBikerId());

        Feedback feedback = new Feedback();
        if (createFeedbackRequest.getRate() > 5 || createFeedbackRequest.getRate() < 1)
            throw new RuntimeException("The rate should be between 1 to 5");

        feedback.setComment(createFeedbackRequest.getComment());
        feedback.setRate(createFeedbackRequest.getRate());
        feedback.setBikerId(createFeedbackRequest.getBikerId());
        feedback.setCustomerId(createFeedbackRequest.getCustomerId());
        feedback.setDeliveryId(createFeedbackRequest.getDeliveryId());
        feedbackRepository.save(feedback);

        biker.setReviewNumbers(biker.getReviewNumbers() + 1);
        biker.setReviewSum((biker.getReviewSum()) + createFeedbackRequest.getRate());

        bikerRepository.save(biker);

        return feedback;
    }

    @Override
    public Page<Feedback> getAllOrderByDeliveryDate(FeedbackPageRequest feedbackPageRequest) {
        Pageable pageable = PageRequest.of(
                feedbackPageRequest.getPageNumber(),
                feedbackPageRequest.getPageSize(),
                Sort.by(Sort.Order.desc("deliveryDate"))
        );
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks;
    }

    @Override
    public Page<Feedback> getBikerFeedback(Pageable pageable, Long bikerId) {
        Page<Feedback> feedback = feedbackRepository.findAllByBikerId(pageable, bikerId);
        return feedback;
    }
}
