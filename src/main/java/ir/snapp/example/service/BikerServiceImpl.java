package ir.snapp.example.service;

import ir.snapp.example.config.filter.UserDetailsImpl;
import ir.snapp.example.model.Biker;
import ir.snapp.example.model.Manager;
import ir.snapp.example.model.dto.request.BikerResponse;
import ir.snapp.example.repository.BikerRepository;
import ir.snapp.example.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BikerServiceImpl implements BikerService
{
    private final BikerRepository bikerRepository;
    private final ManagerRepository managerRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    void init()
    {
        Biker biker = new Biker();
        biker.setName("admin");
        biker.setUsername("admin");
        biker.setPassword(encoder.encode("admin"));
        biker.setEmail("admin@admin.c");
        bikerRepository.save(biker);
        Manager manager = new Manager();
        manager.setName("managerTest");
        manager.setBikers(new HashSet()
        {{
            add(biker);
        }});
        managerRepository.save(manager);
        bikerRepository.save(biker);
    }

    @Override
    public Double overAll(Long bikerId)
    {
        Biker biker = bikerRepository.findById(bikerId);

        if (biker == null) throw new RuntimeException("Biker not found for ID: " + bikerId);

        if (biker.getReviewNumbers() == 0)
            throw new RuntimeException("No reviews available for the biker with ID: " + bikerId);


        double averageRating = (double) biker.getReviewSum() / biker.getReviewNumbers();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(averageRating));
    }

    @Override
    public List<BikerResponse> getRatings()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Biker> bikers = bikerRepository.findAllByManager(userDetails.getUsername());
        return bikers.stream().map(b -> new BikerResponse(b)).collect(Collectors.toList());
    }
}
