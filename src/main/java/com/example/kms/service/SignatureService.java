package com.example.kms.service;

import com.example.kms.entity.*;
import com.example.kms.form.SignatureForm;
import com.example.kms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignatureService {
    private final SignatureRepository signatureRepository;
    private final ImageRepository imageRepository;
    private final OperationRepository operationRepository;
    public Signature createSignature(SignatureForm form) {
        Image image = imageRepository.findById(form.getImage_id())
                .orElseThrow(() -> new RuntimeException("Not found image with id = " + form.getImage_id()));
        Operation operation = operationRepository.findById(form.getOperation_id())
                .orElseThrow(() -> new RuntimeException("Not found operation with id = " + form.getImage_id()));
        return signatureRepository.save(new Signature(form.isGive(), image, operation));
    }

    public List<Signature> getAllSignatures() {
        return signatureRepository.findAll();
    }

    public Signature getSignatureById(Integer signatureId) {
        return signatureRepository.findById(signatureId)
                .orElseThrow(() -> new RuntimeException("Not found signature with id = " + signatureId));
    }

    /*public Signature updateSignature(Integer id, SignatureForm form) {
        Signature signature = signatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found signature with id = " + id));
        //...
        return signatureRepository.save(signature);
    }*/

    public List<Signature> getAllSignaturesByOperationId(Integer operationId){
        if (!operationRepository.existsById(operationId)) {
            throw new RuntimeException("Not found operation with id = " + operationId);
        }
        return signatureRepository.findAllByOperation(operationRepository.findById(operationId));
    }
}
