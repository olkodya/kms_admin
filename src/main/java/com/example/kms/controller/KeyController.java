package com.example.kms.controller;

import com.example.kms.entity.Key;
import com.example.kms.form.KeyForm;
import com.example.kms.service.KeyService;
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
public class KeyController {
    private final KeyService service;

    @Operation(summary = "Get all keys", description = "Returns keys data")
    @GetMapping("/keys")
    public ResponseEntity<List<Key>> getAllKeys() {
        var keys = service.getAllKeys();
        if (keys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }
    @Operation(summary = "Key creation", description = "Returns key data after successful creation")
    @PostMapping("/keys")
    public ResponseEntity<Key> createKey(@RequestBody KeyForm form) {
        return new ResponseEntity<>(service.createKey(form), HttpStatus.CREATED);
    }
    @Operation(summary = "Generate QR for key", description = "Returns updated key data")
    @PutMapping("/keys/{keyId}/QRs")
    public ResponseEntity<Key> generateQRForKey(@PathVariable("keyId") Integer keyId) {
        return new ResponseEntity<>(service.generateQRForKey(keyId), HttpStatus.OK);
    }

    @Operation(summary = "Get key by QR", description = "Returns key data")
    @GetMapping("/QRs/{QR}/keys")
    public ResponseEntity<Key> getKeyByQR(@PathVariable("QR") String QR) {
        var key = service.getKeyByQR(QR);
        if (key == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(key, HttpStatus.OK);
    }

    @Operation(summary = "Update key", description = "Returns updated key data")
    @PutMapping("/keys/{id}")
    public ResponseEntity<Key> updateKey(@PathVariable("id") Integer id,@RequestBody KeyForm form) {
        return new ResponseEntity<>(service.updateKey(id, form), HttpStatus.OK);
    }

    @Operation(summary = "Get key by id", description = "Returns key data")
    @GetMapping("/keys/{id}")
    public ResponseEntity<Key> getKeyById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getKeyById(id), HttpStatus.OK);
    }
}
