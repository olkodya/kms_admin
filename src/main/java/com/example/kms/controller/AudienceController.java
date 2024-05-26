package com.example.kms.controller;

import com.example.kms.entity.Audience;
import com.example.kms.form.AudienceForm;
import com.example.kms.service.AudienceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "https://kmsadmin-production.up.railway.app"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AudienceController {
    private final AudienceService service;
    @Operation(summary = "Get all audiences", description = "Returns audiences data")
    @GetMapping("/audiences")
    public ResponseEntity<List<Audience>> getAllAudiences() {
        var audiences = service.getAllAudiences();
        if (audiences.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(audiences, HttpStatus.OK);
    }
    @Operation(summary = "Audience creation", description = "Returns audience data after successful creation")
    @PostMapping("/audiences")
    public ResponseEntity<Audience> createAudience(@RequestBody AudienceForm form) {
        return new ResponseEntity<>(service.createAudience(form), HttpStatus.CREATED);
    }

    @Operation(summary = "Get audience by id", description = "Returns audience data")
    @GetMapping("/audiences/{id}")
    public ResponseEntity<Audience> getAudienceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getAudienceById(id), HttpStatus.OK);
    }

    @PutMapping("/permissions/{permissionId}/audiences/{audienceId}")
    @Operation(summary = "Add permission to audience by id", description = "Returns updated audience data")
    public ResponseEntity<Audience> addPermissionToAudience(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "audienceId") Integer employeeId) {
        return new ResponseEntity<>(service.addPermissionToAudience(permissionId, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/permissions/{permissionId}/audiences/{audienceId}")
    @Operation(summary = "Delete permission from audience by id")
    public ResponseEntity<HttpStatus> deletePermissionFromEmployee(@PathVariable(value = "permissionId") Integer permissionId, @PathVariable(value = "audienceId") Integer audienceId) {
        service.deletePermissionFromAudience(permissionId, audienceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update audience by id", description = "Returns updated audience data")
    @PutMapping("/audiences/{id}")
    public ResponseEntity<Audience> updateAudience(@PathVariable("id") Integer id, @RequestBody AudienceForm form) {
        return new ResponseEntity<>(service.updateAudience(id, form), HttpStatus.OK);
    }
}
