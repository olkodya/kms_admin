package com.example.kms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "divisions")
@Table
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "division_id")*/
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer division_id;
    private String name;
    /*@ManyToMany @JoinTable(
            name = "divisions_employees",
            joinColumns = @JoinColumn(name = "division_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees = new HashSet<>();*/
    @JsonIgnore
    @ManyToMany(mappedBy = "divisions")
    private Set<Employee> employees = new HashSet<>();

    /*@ManyToMany(mappedBy = "divisions")
    private Set<Permission> permissions = new HashSet<>();*/

    @ManyToMany
    @JoinTable(
            name = "permissions_divisions",
            joinColumns = @JoinColumn(name = "division_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public Division(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    /*public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }*/
    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getDivisions().add(this);
    }

    public void removePermission(Integer permission_id) {
        Permission permission = this.permissions.stream().filter(p -> Objects.equals(p.getPermission_id(), permission_id)).findFirst().orElse(null);
        if (permission != null) {
            this.permissions.remove(permission);
            permission.getDivisions().remove(this);
        }
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDivision_id() {
        return division_id;
    }

   /* public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getDivisions().add(this);
    }

    public void removeEmployee(Integer employee_id) {
        Employee employee = this.employees.stream().filter(e -> Objects.equals(e.getEmployee_id(), employee_id)).findFirst().orElse(null);
        if (employee != null) {
            this.employees.remove(employee);
            employee.getDivisions().remove(this);
        }
    }*/
}
