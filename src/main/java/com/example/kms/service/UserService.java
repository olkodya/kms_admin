package com.example.kms.service;

import com.example.kms.entity.User;
import com.example.kms.form.RegForm;
import com.example.kms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(RegForm form, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + id));
        if (form.getUsername() != null)
            user.setUsername(form.getUsername());
        if (form.getPassword() != null) {
            if (passwordEncoder.matches(form.getPassword(), user.getPassword()))
                return null;
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + id));
    }
}
