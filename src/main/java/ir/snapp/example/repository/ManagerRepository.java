package ir.snapp.example.repository;

import ir.snapp.example.model.Biker;
import ir.snapp.example.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>
{
    Biker findById(Long id);
}
