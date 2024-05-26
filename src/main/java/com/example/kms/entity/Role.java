package com.example.kms.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.kms.entity.RolePermission.ADMIN_READ;
import static com.example.kms.entity.RolePermission.ADMIN_UPDATE;
import static com.example.kms.entity.RolePermission.ADMIN_DELETE;
import static com.example.kms.entity.RolePermission.ADMIN_CREATE;
import static com.example.kms.entity.RolePermission.USER_READ;
import static com.example.kms.entity.RolePermission.USER_UPDATE;
import static com.example.kms.entity.RolePermission.USER_DELETE;
import static com.example.kms.entity.RolePermission.USER_CREATE;

@RequiredArgsConstructor
public enum Role {

    USER(Set.of(
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    )),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,
            USER_READ,
            USER_UPDATE,
            USER_DELETE,
            USER_CREATE
    ));

    @Getter
    private final Set<RolePermission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getRolePermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}