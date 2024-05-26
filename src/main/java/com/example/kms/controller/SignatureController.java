package com.example.kms.controller;

import com.example.kms.entity.Signature;
import com.example.kms.form.SignatureForm;
import com.example.kms.service.SignatureService;
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
public class SignatureController {
    private final SignatureService service;

    @Operation(summary = "Get all signatures", description = "Returns signatures data")
    @GetMapping("/signatures")
    public ResponseEntity<List<Signature>> getAllSignatures() {
        var signatures = service.getAllSignatures();
        if (signatures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(signatures, HttpStatus.OK);
    }
    @Operation(summary = "Signature creation", description = "Returns signature data after successful creation")
    @PostMapping("/signatures")
    public ResponseEntity<Signature> createSignature(@RequestBody SignatureForm form) {
        return new ResponseEntity<>(service.createSignature(form), HttpStatus.CREATED);
    }
    /*@Operation(summary = "Update signature", description = "Returns updated signature data")
    @PutMapping("/signatures/{id}")
    public ResponseEntity<Signature> updateSignature(@PathVariable("id") Integer id, SignatureForm form) {
        return new ResponseEntity<>(service.updateSignature(id, form), HttpStatus.OK);
    }*/

    @Operation(summary = "Get signature by id", description = "Returns signature data")
    @GetMapping("/signatures/{id}")
    public ResponseEntity<Signature> getSignatureById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getSignatureById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get signatures by operation id", description = "Returns signatures data")
    @GetMapping("/operations/{operationId}/signatures")
    public ResponseEntity<List<Signature>> getAllSignaturesByOperationId(@PathVariable(value = "operationId") Integer operationId) {
        return new ResponseEntity<>(service.getAllSignaturesByOperationId(operationId), HttpStatus.OK);
    }
}
