package ir.snapp.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "biker")
public class Biker extends User {

    private Manager manager;

    private Long reviewNumbers = 0l;

    private Long reviewSum = 0l;

    @ManyToOne(targetEntity = Manager.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    public Manager getManager() {
        return manager;
    }
}
