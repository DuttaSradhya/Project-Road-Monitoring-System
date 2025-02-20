package com.example.report_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.report_service.model.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
    
}
