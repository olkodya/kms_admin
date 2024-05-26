package com.example.kms.form;

import com.example.kms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private String access_token;
    private String refresh_token;
    private Integer user_id;
    private String username;
    private Employee employee;
}