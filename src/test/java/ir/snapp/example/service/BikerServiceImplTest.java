package ir.snapp.example.service;

import ir.snapp.example.model.Biker;
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
class BikerServiceImplTest
{
    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackServiceImp feedbackServiceImp;

    @Mock
    BikerRepository bikerRepository;

    @InjectMocks
    BikerServiceImpl bikerService;

    private Biker biker;

    @BeforeEach
    public void init()
    {
        biker = new Biker();
        biker.setId(1L);
        biker.setReviewSum(10L);
        biker.setReviewNumbers(2L);
    }

    @Test
    public void testOverAllWithNoRate()
    {
        biker.setReviewNumbers(0L);
        when(bikerRepository.findById(biker.getId())).thenReturn(biker);
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("This biker doesnt have any rate!" + biker.getReviewNumbers());
        });

    }

    @Test
    public void testOverAllWithNoBiker()
    {
        biker.setId(2L);
        when(bikerRepository.findById(biker.getId())).thenThrow(
                new RuntimeException("Biker not found for ID:" + biker.getId())
        );
    }

    @Test
    public void testOverAll()
    {
        when(bikerRepository.findById(biker.getId())).thenReturn(biker);
        Double averageRating = bikerService.overAll(biker.getId());
        assertEquals (5.00,averageRating);

    }

}