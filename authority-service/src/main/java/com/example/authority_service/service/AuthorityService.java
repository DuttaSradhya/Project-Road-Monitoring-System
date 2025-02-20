package com.example.authority_service.service;

//import org.hibernate.mapping.List;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.authority_service.controller.ReportClient;
import com.example.authority_service.dto.AuthorityRequest;
import com.example.authority_service.dto.AuthorityResponse;
import com.example.authority_service.dto.LoginRequest;
import com.example.authority_service.model.Authority;
import com.example.authority_service.repository.AuthorityRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class AuthorityService {

    private final AuthorityRepository userRepository;
    private final ReportClient reportClient;

    public AuthorityResponse createUser(AuthorityRequest userRequest){
        Authority user = Authority.builder()
            .name(userRequest.getName())
            .username(userRequest.getUsername())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .build();

        userRepository.save(user);
        log.info("User {} is saved", user.getUsername());
        return AuthorityResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .username(userRequest.getUsername())
            .email(user.getEmail())
            .build(); 

    } 

    public List<AuthorityResponse> getAllUsers(){
        List<Authority> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserResponse(user)).toList();
    }

    private AuthorityResponse mapToUserResponse(Authority user){
        return AuthorityResponse.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .build(); 
    }

    public AuthorityResponse getUserById(String id) {
        Optional<Authority> userOptional = userRepository.findById(id);

        Authority user = userOptional.get();
        return AuthorityResponse.builder()
            .id(user.getId())
            .name(user.getUsername()) 
            .email(user.getEmail()) 
            .build();
    }

    public void updateUserById(String id, AuthorityRequest userRequest){
        Optional<Authority> userOptional = userRepository.findById(id);
        Authority user = userOptional.get();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        userRepository.save(user);
        log.info("Admin {} is updated", user.getUsername());
    }

    public Authority getUserByUsername(String username) {
        Authority userOptional = userRepository.findByUsername(username);

        return userOptional;
        // // User user = userOptional.get();
        // return UserResponse.builder()
        //     .id(userOptional.getId())
        //     .name(userOptional.getUsername()) 
        //     .email(userOptional.getEmail()) 
        //     .build();
    }

    public List<Map<String, Object>> searchReportsByDescription(String description) {
        return reportClient.searchReportsByDescription(description);
    }

    public List<Map<String, Object>> searchReportsByCategory(String categoryName) {
        return reportClient.searchReportsByCategory(categoryName);
    }

    public List<Map<String, Object>> searchReportsByStatus(String status) {
        return reportClient.searchReportsByStatus(status);
    }

    public List<String> getAllEmails() {
        return userRepository.findAllEmails().stream()
                .map(Authority::getEmail)
                .collect(Collectors.toList());    }

    public AuthorityResponse login(LoginRequest loginRequest) {
        Optional<Authority> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()) {
            Authority authority = userOptional.get();
            if (authority.getPassword().equals(loginRequest.getPassword())) {
                return AuthorityResponse.builder()
                    .id(authority.getId())
                    .name(authority.getName())
                    .username(authority.getUsername())
                    .email(authority.getEmail())
                    .build();
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    
}
