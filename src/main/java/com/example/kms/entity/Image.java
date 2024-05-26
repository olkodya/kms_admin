package com.example.kms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.relational.core.mapping.Table;

import static java.sql.Types.LONGVARBINARY;

@Data
@Builder
@Entity(name = "images")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer image_id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String type;
    @Setter
    @Lob
    @JdbcTypeCode(LONGVARBINARY)
    private byte[] data;

    @JsonIgnore
    public byte[] getData() {
        return data;
    }

}
