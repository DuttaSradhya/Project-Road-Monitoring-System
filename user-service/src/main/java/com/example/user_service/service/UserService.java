package com.example.user_service.service;

//import org.hibernate.mapping.List;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.user_service.controller.ReportClient;
import com.example.user_service.dto.LoginRequest;
import com.example.user_service.dto.UserRequest;
import com.example.user_service.dto.UserResponse;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class UserService {

    private final UserRepository userRepository;
    private final ReportClient reportClient;

    public UserResponse createUser(UserRequest userRequest){
        User user = User.builder()
            .name(userRequest.getName())
            .username(userRequest.getUsername())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .build();

        userRepository.save(user);
        log.info("User {} is saved", user.getUsername());

        return UserResponse.builder()
            .id(user.getId())
            .name(user.getName())
            .username(user.getUsername())
            .email(user.getEmail())
            .build();

    } 

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserResponse(user)).toList();
    }

    private UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getUsername())
            .username(user.getUsername())
            .email(user.getEmail())
            .build(); 
    }

    public UserResponse getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.get();
        return UserResponse.builder()
            .id(user.getId())
            .name(user.getUsername())
            .username(user.getUsername()) 
            .email(user.getEmail()) 
            .build();
    }

    public void updateUserById(String id, UserRequest userRequest){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        userRepository.save(user);
        log.info("User {} is updated", user.getUsername());
    }

    public User getUserByUsername(String username) {
        User userOptional = userRepository.findByUsername(username);

        return userOptional;
        // // User user = userOptional.get();
        // return UserResponse.builder()
        //     .id(userOptional.getId())
        //     .name(userOptional.getUsername()) 
        //     .email(userOptional.getEmail()) 
        //     .build();
    }

    public List<Map<String, Object>> searchReportsByUser(String userID) {
        return reportClient.searchReportsByUser(userID);
    }

    public UserResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
