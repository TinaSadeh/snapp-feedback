package ir.snapp.example.repository;

import ir.snapp.example.model.Biker;
import ir.snapp.example.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikerRepository extends JpaRepository<Biker, Integer>
{
    Biker findById(Long id);
    @Query("FROM Biker b WHERE b.manager.username = ?1 ORDER BY b.reviewSum / b.reviewNumbers DESC ")
    List<Biker> findAllByManager(String username);

}
