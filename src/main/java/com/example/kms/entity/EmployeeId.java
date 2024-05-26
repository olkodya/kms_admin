package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Entity(name = "employee_ids")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private Date start_date;
    private Date end_date;
    private Boolean not_expired;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeId(String number, Date start_date, Date end_date, Boolean not_expired, Employee employee) {
        this.number = number;
        this.start_date = start_date;
        this.end_date = end_date;
        this.not_expired = not_expired;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isNot_expired() {
        return not_expired;
    }

    public void setNot_expired(boolean not_expired) {
        this.not_expired = not_expired;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
