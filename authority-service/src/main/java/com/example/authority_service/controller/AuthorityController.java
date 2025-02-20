package com.example.authority_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.authority_service.dto.AuthorityRequest;
import com.example.authority_service.dto.AuthorityResponse;
import com.example.authority_service.dto.LoginRequest;
import com.example.authority_service.model.Authority;
import com.example.authority_service.service.AuthorityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AuthorityController {

    private final AuthorityService  authorityService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorityResponse createUser(@RequestBody AuthorityRequest userRequest) {
        return authorityService.createUser(userRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorityResponse> getAllUsers() {
        return authorityService.getAllUsers();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorityResponse getUserById(@PathVariable String id){
        return authorityService.getUserById(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateUserById(@PathVariable String id, @RequestBody AuthorityRequest userRequest){
        authorityService.updateUserById(id, userRequest);
    }

    @GetMapping("/name/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Authority getUserByUsername(@PathVariable String username){
        return authorityService.getUserByUsername(username);
    }

    @GetMapping("/reports/desciption")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> searchReportsByDescription(@RequestParam String description) {
        return authorityService.searchReportsByDescription(description);
    }

    @GetMapping("/reports/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> searchReportsByCategory(@RequestParam String category) {
        return authorityService.searchReportsByCategory(category);
    }

    @GetMapping("/reports/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> searchReportsByStatus(@RequestParam String status) {
        return authorityService.searchReportsByStatus(status);
    }

    @GetMapping("/emails")
    public ResponseEntity<List<String>> getAllEmails() {
        List<String> emails = authorityService.getAllEmails();
        return ResponseEntity.ok(emails);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthorityResponse login(@RequestBody LoginRequest loginRequest){
        log.info(loginRequest.toString());
        return authorityService.login(loginRequest);
    }


}
