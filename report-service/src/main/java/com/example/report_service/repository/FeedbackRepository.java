package com.example.report_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.report_service.model.FeedBack;

public interface FeedbackRepository extends MongoRepository<FeedBack, String>{

}
