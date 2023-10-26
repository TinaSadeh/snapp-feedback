package ir.snapp.example.service;

import ir.snapp.example.model.Biker;
import ir.snapp.example.model.Feedback;
import ir.snapp.example.model.dto.CreateFeedbackRequest;
import ir.snapp.example.repository.BikerRepository;
import ir.snapp.example.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FeedbackServiceImpTest
{
    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackServiceImp feedbackServiceImp;

    @Mock
    BikerRepository bikerRepository;

    @InjectMocks
    BikerServiceImpl bikerService;

    private CreateFeedbackRequest createFeedbackRequest;
    private Biker biker;

    @BeforeEach
    public void init()
    {
        createFeedbackRequest = new CreateFeedbackRequest();
        createFeedbackRequest.setRate(1);
        createFeedbackRequest.setComment("Good");
        createFeedbackRequest.setBikerId(1L);
        createFeedbackRequest.setDeliveryId(1L);

        biker = new Biker();
        biker.setId(1L);
    }

    @Test
    public void testCreateFeedbackWithNullBikerId()
    {
        createFeedbackRequest.setBikerId(1L);
        when(bikerRepository.findById(createFeedbackRequest.getBikerId())).thenThrow(
                new RuntimeException("Biker not found for ID:" + createFeedbackRequest.getBikerId())
        );

    }

    @Test
    public void testCreateFeedbackWithInvalidRate()
    {
        createFeedbackRequest.setRate(10);
        when(bikerRepository.findById(createFeedbackRequest.getBikerId())).thenReturn(biker);
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("The rate should be between 1 to 5" + createFeedbackRequest.getRate());
        });
    }

    @Test
    public void testCreateFeedback()
    {
        when(bikerRepository.findById(createFeedbackRequest.getBikerId())).thenReturn(biker);
        Feedback createFeedback = feedbackServiceImp.create(createFeedbackRequest);
        assertNotNull(createFeedback);
        assertEquals(createFeedback.getBikerId(),createFeedbackRequest.getBikerId());
        assertEquals(createFeedback.getDeliveryId(),createFeedbackRequest.getDeliveryId());
        assertEquals(createFeedback.getCustomerId(),createFeedbackRequest.getCustomerId());
        assertEquals(createFeedback.getRate(),createFeedbackRequest.getRate());
        assertEquals(createFeedback.getComment(),createFeedbackRequest.getComment());
    }

}