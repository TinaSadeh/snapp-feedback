package ir.snapp.example.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.snapp.example.model.Biker;

public class BikerResponse {

    @JsonProperty
    private String username;
    @JsonProperty
    private String rate;
    @JsonProperty
    private long feedbackCount;
    @JsonProperty
    private Long userId;

    public BikerResponse(Biker biker) {
        this.username = biker.getUsername();
        this.rate = String.format("%.1f",((double) biker.getReviewSum() / biker.getReviewNumbers()));
        this.feedbackCount = biker.getReviewNumbers();
        this.userId = biker.getId();

    }
}
