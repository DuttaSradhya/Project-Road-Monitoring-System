package com.example.authority_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value  = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Authority {

    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String username;
    @Field
    private String email;
    @Field
    private String password;
}
