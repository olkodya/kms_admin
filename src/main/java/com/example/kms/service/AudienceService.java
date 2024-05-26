package com.example.kms.service;

import com.example.kms.entity.Audience;
import com.example.kms.entity.Image;
import com.example.kms.entity.Permission;
import com.example.kms.form.AudienceForm;
import com.example.kms.repository.AudienceRepository;
import com.example.kms.repository.ImageRepository;
import com.example.kms.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudienceService {
    private final AudienceRepository audienceRepository;
    private final PermissionRepository permissionRepository;
    private final ImageRepository imageRepository;
    public Audience createAudience(AudienceForm form) {
        Image image = imageRepository.findById(form.getImage_id())
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + form.getImage_id()));
        return audienceRepository.save(new Audience(form.getNumber(), form.getCapacity(), form.getExist(), form.getSignalisation(), form.getAudience_type(), image));
    }

    public List<Audience> getAllAudiences() {
        return audienceRepository.findAll();
    }

    public Audience getAudienceById(Integer id) {
        return audienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + id));
    }

    public void deletePermissionFromAudience(Integer permissionId, Integer audienceId) {
        Audience audience = audienceRepository.findById(audienceId)
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + audienceId));
        audience.removePermission(permissionId);
        audienceRepository.save(audience);
        /*Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
        permission.removeAudience(audienceId);
        permissionRepository.save(permission);*/
    }

    public Audience addPermissionToAudience(Integer permissionId, Integer audienceId) {
        /*return permissionRepository.findById(permissionId).map(permission -> {
            Audience audience = audienceRepository.findById(audienceId)
                    .orElseThrow(() -> new RuntimeException("Not found audience with id = " + audienceId));
            permission.addAudience(audience);
            permissionRepository.save(permission);
            return audience;
        }).orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));*/
        return audienceRepository.findById(audienceId).map(audience -> {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
            audience.addPermission(permission);
            audienceRepository.save(audience);
            return audience;
        }).orElseThrow(() -> new RuntimeException("Not found audience with id = " + audienceId));
    }

    public Audience updateAudience(Integer id, AudienceForm form) {
        Audience audience = audienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found audience with id = " + id));
        if (form.getNumber() != null) {
            audience.setNumber(form.getNumber());
        }
        if (form.getCapacity() != null) {
            audience.setCapacity(form.getCapacity());
        }
        if (form.getExist() != null) {
            audience.setExist(form.getExist());
        }
        if (form.getSignalisation() != null) {
            audience.setSignalisation(form.getSignalisation());
        }
        if (form.getAudience_type() != null) {
            audience.setAudience_type(form.getAudience_type());
        }
        if (form.getImage_id() != null) {
            Image image = imageRepository.findById(form.getImage_id())
                    .orElseThrow(() -> new RuntimeException("Not found audience with id = " + form.getImage_id()));
            audience.setImage(image);
        }
        return audienceRepository.save(audience);
    }
}
