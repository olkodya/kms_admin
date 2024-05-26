package com.example.kms.controller;

import com.example.kms.entity.User;
import com.example.kms.form.RegForm;
import com.example.kms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "https://kms2-production.up.railway.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService service;
    @Operation(summary = "Get all users", description = "Returns users data")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        var users = service.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Update user data", description = "Returns updated user data")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody RegForm form) {
        var updatedUser = service.updateUser(form, id);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Get user by id", description = "Returns user data by id")
    @GetMapping({ "/users/{id}" })
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }
}
