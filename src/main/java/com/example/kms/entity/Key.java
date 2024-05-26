package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Entity(name = "keys")
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer key_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "audience_id")
    private Audience audience;
    @Enumerated(EnumType.STRING)
    private KeyState key_state;
    private Boolean main;
    private String QR;

    public Key(Audience audience, KeyState key_state, Boolean main) {
        this.audience = audience;
        this.key_state = key_state;
        this.main = main;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public KeyState getKey_state() {
        return key_state;
    }

    public void setKey_state(KeyState key_state) {
        this.key_state = key_state;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Integer getKey_id() {
        return key_id;
    }
}
