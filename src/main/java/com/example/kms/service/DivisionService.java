package com.example.kms.service;

import com.example.kms.entity.Division;
import com.example.kms.entity.Permission;
import com.example.kms.form.DivisionForm;
import com.example.kms.repository.DivisionRepository;
import com.example.kms.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {
    private final DivisionRepository divisionRepository;
    private final PermissionRepository permissionRepository;
    public Division createDivision(DivisionForm form) {
        return divisionRepository.save(new Division(form.getName()));
    }

    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    public Division getDivisionById(Integer divisionId) {
        return divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
    }

    public void deletePermissionFromDivision(Integer permissionId, Integer divisionId) {
        /*Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
        permission.removeDivision(divisionId);
        permissionRepository.save(permission);*/
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
        division.removePermission(permissionId);
        divisionRepository.save(division);
    }

    public Division addPermissionToDivision(Integer permissionId, Integer divisionId) {
        /*return permissionRepository.findById(permissionId).map(permission -> {
            Division division = divisionRepository.findById(divisionId)
                    .orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
            permission.addDivision(division);
            permissionRepository.save(permission);
            return division;
        }).orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));*/
        return divisionRepository.findById(divisionId).map(division -> {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new RuntimeException("Not found permission with id = " + permissionId));
            division.addPermission(permission);
            divisionRepository.save(division);
            return division;
        }).orElseThrow(() -> new RuntimeException("Not found division with id = " + divisionId));
    }

    public Division updateDivision(Integer id, DivisionForm form) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found division with id = " + id));
        if (form.getName()!= null) division.setName(form.getName());
        return divisionRepository.save(division);
    }
}
