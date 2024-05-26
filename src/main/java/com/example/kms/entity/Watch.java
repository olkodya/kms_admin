package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Entity(name = "watches")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watch_id")
    private Integer watchId;
    @Setter
    @Column(name = "building_number")
    private String buildingNumber;

    public Watch(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
