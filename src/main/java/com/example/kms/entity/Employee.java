package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity(name = "employees")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;
    @Setter
    @Column(name = "first_name")
    private String firstName;
    @Setter
    @Column(name = "second_name")
    private String secondName;
    @Setter
    @Column(name = "middle_name")
    private String middleName;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
    @Setter
    private String QR;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type")
    private EmployeeType employeeType;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus employeeStatus;


    /*@ManyToMany(mappedBy = "employees")
    private Set<Division> divisions = new HashSet<>();*/
    @ManyToMany @JoinTable(
            name = "divisions_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "division_id"))
    private Set<Division> divisions = new HashSet<>();

    /*@ManyToMany(mappedBy = "employees")
    private Set<Permission> permissions = new HashSet<>();*/
    @ManyToMany
    @JoinTable(
            name = "permissions_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public Employee(String firstName, String secondName, String middleName, Image image, EmployeeType employeeType, EmployeeStatus employeeStatus) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.image = image;
        this.employeeType = employeeType;
        this.employeeStatus = employeeStatus;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getEmployees().add(this);
    }

    public void removePermission(Integer permission_id) {
        Permission permission = this.permissions.stream().filter(p -> Objects.equals(p.getPermission_id(), permission_id)).findFirst().orElse(null);
        if (permission != null) {
            this.permissions.remove(permission);
            permission.getEmployees().remove(this);
        }
    }

    public void addDivision(Division division) {
        this.divisions.add(division);
        division.getEmployees().add(this);
    }

    public void removeDivision(Integer division_id) {
        Division division = this.divisions.stream().filter(d -> Objects.equals(d.getDivision_id(), division_id)).findFirst().orElse(null);
        if (division != null) {
            this.divisions.remove(division);
            division.getEmployees().remove(this);
        }
    }

    /*public void setDivisions(Set<Division> divisions) {
        this.divisions = divisions;
    }*/
    /*public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }*/

}
