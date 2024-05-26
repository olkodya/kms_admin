package com.example.kms.service;

import com.example.kms.entity.*;
import com.example.kms.form.*;
import com.example.kms.repository.EmployeeRepository;
import com.example.kms.repository.TokenRepository;
import com.example.kms.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public User createUser(RegForm form) {
        Employee employee = employeeRepository.findById(form.getEmployee_id())
                .orElseThrow(() -> new RuntimeException("Not found employee with id = " + form.getEmployee_id()));
        var user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .employee(employee)
                .role(Role.USER)
                .build();
        var jwtToken = jwtService.generateToken(user);
        userRepository.save(user);
        saveUserToken(user, jwtToken);
        return user;
    }

    public AuthResponse auth(AuthForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
        var user = userRepository.findByUsername(form.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .access_token(jwtToken)
                .refresh_token(refreshToken)
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .employee(user.getEmployee())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var userTokens = tokenRepository.findAllValidTokenByUser(user);
        ArrayList<Token> validUserTokens = new ArrayList<>();
        for (Token token : userTokens) {
            if ((!token.isRevoked() || !token.isExpired()) && token.getUser() == user) {
                validUserTokens.add(token);
            }
        }
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public TokenRefreshResponse refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtService.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.userRepository.findByUsername(userName)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                return new TokenRefreshResponse(accessToken, jwtService.generateRefreshToken(user));
            }
        }
        return null;
    }

}
