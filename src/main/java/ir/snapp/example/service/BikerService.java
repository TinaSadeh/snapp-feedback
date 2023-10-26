package ir.snapp.example.service;


import ir.snapp.example.model.dto.request.BikerResponse;

import java.util.List;

public interface BikerService
{
    Double overAll(Long bikerId);

    List<BikerResponse> getRatings();
}
