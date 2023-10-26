package ir.snapp.example.repository;

import ir.snapp.example.model.Feedback;
import ir.snapp.example.model.dto.FeedbackPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long>
{
    Page<Feedback> findAllByBikerId(Pageable pageable, Long bikerId);

}
