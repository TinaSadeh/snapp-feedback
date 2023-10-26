package ir.snapp.example.model.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class CreateFeedbackRequest
{
    private String comment;

    private int rate;

    private long deliveryId;

    private long customerId;

    private long bikerId;

}
