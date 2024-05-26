package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Entity(name = "operations")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operation_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "key_id", nullable=false)
    private Key key;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable=false)
    private Employee employee;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shift_id", nullable=false)
    private Shift shift;
    private Timestamp give_date_time;
    private Timestamp return_date_time;

    public Operation(Key key, Employee employee, Shift shift, Timestamp give_date_time, Timestamp return_date_time) {
        this.key = key;
        this.employee = employee;
        this.shift = shift;
        this.give_date_time = give_date_time;
        this.return_date_time = return_date_time;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Timestamp getGive_date_time() {
        return give_date_time;
    }

    public void setGive_date_time(Timestamp give_date_time) {
        this.give_date_time = give_date_time;
    }

    public Timestamp getReturn_date_time() {
        return return_date_time;
    }

    public void setReturn_date_time(Timestamp return_date_time) {
        this.return_date_time = return_date_time;
    }

    public Integer getOperation_id() {
        return operation_id;
    }
}
