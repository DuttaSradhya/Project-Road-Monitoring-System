package com.example.authority_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.authority_service.model.Authority;

public interface AuthorityRepository extends MongoRepository<Authority, String>{
    Authority findByUsername(String username);

    @Query(value = "{}", fields = "{ 'email' : 1 }")
    List<Authority> findAllEmails();
    Optional<Authority> findByEmail(String email);


}
