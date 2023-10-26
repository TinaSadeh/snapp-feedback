package ir.snapp.example.controller;

import ir.snapp.example.model.Feedback;
import ir.snapp.example.model.dto.CreateFeedbackRequest;
import ir.snapp.example.model.dto.FeedbackPageRequest;
import ir.snapp.example.model.dto.request.BikerResponse;
import ir.snapp.example.service.BikerService;
import ir.snapp.example.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final BikerService bikerService;


    /**
     * @param createFeedbackRequest
     * As a customer, I want to rate my delivery experience so that the service can be improved.
     * @return
     */
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Feedback createFeedback(@Valid @RequestBody CreateFeedbackRequest createFeedbackRequest) {
        Feedback feedback = feedbackService.create(createFeedbackRequest);
        return feedback;
    }


    /**
     * @param feedbackPageRequest
     * As a manager, I want to view customer feedback sorted by delivery date so that I can understand recent performance.
     * @return
     */
    @RequestMapping(path = "/feedbacks", method = RequestMethod.GET)
    public Page<Feedback> getAll(FeedbackPageRequest feedbackPageRequest) {
        Page<Feedback> feedbackPageable = feedbackService.getAllOrderByDeliveryDate(feedbackPageRequest);
        return feedbackPageable;
    }


    /**
     * @param bikerId
     * @param pageable
     * As a manager, I want to view feedback for each driver to evaluate individual performance.
     * @return
     */
    @RequestMapping(path = "/{bikerId}", method = RequestMethod.GET)
    public Page<Feedback> getBikerFeedback(@PathVariable("bikerId") Long bikerId, Pageable pageable) {
        Page<Feedback> feedbacks = feedbackService.getBikerFeedback(pageable, bikerId);
        return feedbacks;
    }

    /**
     * As a manager, I want to view feedback based on ratings to evaluate the overall service quality.
     * @return
     */
    @RequestMapping(path = "/ratings", method = RequestMethod.GET)
    public List<BikerResponse> getRatings() {
        List<BikerResponse> bikers = bikerService.getRatings();
        return bikers;
    }

    /**
     * @param bikerId
     * I want to know my average rating so that I can understand how well I am performing.
     * @return
     */
    @RequestMapping(path = "/{bikerId}/overall", method = RequestMethod.GET)
    public Double getBikerOverAllFeedback(@PathVariable("bikerId") Long bikerId) {
        return bikerService.overAll(bikerId);
    }
}
