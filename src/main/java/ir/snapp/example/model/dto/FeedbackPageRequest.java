package ir.snapp.example.model.dto;

import lombok.Data;

@Data
public class FeedbackPageRequest {
    private int pageNumber = 0;
    private int pageSize = 10;
}
