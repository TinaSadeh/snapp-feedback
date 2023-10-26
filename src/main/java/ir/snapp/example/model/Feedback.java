package ir.snapp.example.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@Table(name = "feedback")
public class Feedback
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String comment;

    @Min(1)
    @Max(5)
    private int rate;

    private Long deliveryId;

    private Long customerId;

    private Long bikerId;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    @CreationTimestamp
    private Date deliveryDate;
}
