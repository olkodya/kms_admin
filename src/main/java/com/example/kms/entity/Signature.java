package com.example.kms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Entity(name = "signatures")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer signature_id;
    private boolean give;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_id")
    @JsonIgnore
    private Operation operation;

    public Signature(boolean give, Image image, Operation operation) {
        this.give = give;
        this.image = image;
        this.operation = operation;
    }

    public boolean isGive() {
        return give;
    }

    public void setGive(boolean give) {
        this.give = give;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
