package ir.snapp.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Manager")
public class Manager extends User {

    private Set<Biker> bikers;

    @OneToMany(targetEntity=Biker.class, fetch = FetchType.EAGER, mappedBy = "manager")
    public Set<Biker> getBikers() {
        return bikers;
    }
}
