package com.example.kms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "permissions")
@Table
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "permission_id")*/
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permission_id;
    private String name;

    /*@ManyToMany
    @JoinTable(
            name = "permissions_employees",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees = new HashSet<>();*/
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Employee> employees = new HashSet<>();

    /*@ManyToMany
    @JoinTable(
            name = "permissions_divisions",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "division_id"))*/
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Division> divisions = new HashSet<>();

    /*@ManyToMany
    @JoinTable(
            name = "permissions_audiences",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "audience_id"))*/
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Audience> audiences = new HashSet<>();

    public Permission(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<Division> getDivisions() {
        return divisions;
    }

    public Set<Audience> getAudiences() {
        return audiences;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void setDivisions(Set<Division> divisions) {
        this.divisions = divisions;
    }

    public void setAudiences(Set<Audience> audiences) {
        this.audiences = audiences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    /*public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.getPermissions().add(this);
    }

    public void removeEmployee(Integer employee_id) {
        Employee employee = this.employees.stream().filter(e -> Objects.equals(e.getEmployee_id(), employee_id)).findFirst().orElse(null);
        if (employee != null) {
            this.employees.remove(employee);
            employee.getPermissions().remove(this);
        }
    }*/

/*    public void addDivision(Division division) {
        this.divisions.add(division);
        division.getPermissions().add(this);
    }

    public void removeDivision(Integer division_id) {
        Division division = this.divisions.stream().filter(e -> Objects.equals(e.getDivision_id(), division_id)).findFirst().orElse(null);
        if (division != null) {
            this.divisions.remove(division);
            division.getPermissions().remove(this);
        }
    }*/

    /*public void addAudience(Audience audience) {
        this.audiences.add(audience);
        audience.getPermissions().add(this);
    }

    public void removeAudience(Integer audience_id) {
        Audience audience = this.audiences.stream().filter(e -> Objects.equals(e.getAudience_id(), audience_id)).findFirst().orElse(null);
        if (audience != null) {
            this.audiences.remove(audience);
            audience.getPermissions().remove(this);
        }
    }*/
}
