package com.example.kms.service;

import com.example.kms.entity.Permission;
import com.example.kms.form.PermissionForm;
import com.example.kms.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    public Permission createPermission(PermissionForm form) {
        return permissionRepository.save(new Permission(form.getName()));
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
    }

    public Permission updatePermission(Integer id, PermissionForm form) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found permission with id = " + id));
        if (form.getName() != null) permission.setName(form.getName());
        return permissionRepository.save(permission);
    }
}
