package com.example.kms.controller;

import com.example.kms.entity.Permission;
import com.example.kms.form.PermissionForm;
import com.example.kms.service.PermissionService;
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
public class PermissionController {
    private final PermissionService service;
    @Operation(summary = "Get all permissions", description = "Returns permissions data")
    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        var permissions = service.getAllPermissions();
        if (permissions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }
    @Operation(summary = "Get permission by id", description = "Returns permission data")
    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getPermissionById(id), HttpStatus.OK);
    }
    @Operation(summary = "Permission creation", description = "Returns permission data after successful creation")
    @PostMapping("/permissions")
    public ResponseEntity<Permission> createPermission(@RequestBody PermissionForm form) {
        return new ResponseEntity<>(service.createPermission(form), HttpStatus.CREATED);
    }

    @Operation(summary = "Update permission by id (change name)", description = "Returns updated permission data")
    @PutMapping("/permissions/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable("id") Integer id, @RequestBody PermissionForm form) {
        return new ResponseEntity<>(service.updatePermission(id, form), HttpStatus.OK);
    }
}
