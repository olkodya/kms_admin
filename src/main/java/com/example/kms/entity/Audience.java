package com.example.kms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "audiences")
@Table
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "audience_id")*/
public class Audience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer audience_id;
    private Integer number;
    private Integer capacity;
    private Boolean exist;
    @Enumerated(EnumType.STRING)
    private Signalisation signalisation;
    @Enumerated(EnumType.STRING)
    private AudienceType audience_type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
    /*@ManyToMany(mappedBy = "audiences")*/
    @ManyToMany
    @JoinTable(
            name = "permissions_audiences",
            joinColumns = @JoinColumn(name = "audience_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();


    public Audience(Integer number, Integer capacity, Boolean exist, Signalisation signalisation, AudienceType audience_type, Image image) {
        this.number = number;
        this.capacity = capacity;
        this.exist = exist;
        this.signalisation = signalisation;
        this.audience_type = audience_type;
        this.image = image;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    /*public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }*/
    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getAudiences().add(this);
    }

    public void removePermission(Integer permission_id) {
        Permission permission = this.permissions.stream().filter(p -> Objects.equals(p.getPermission_id(), permission_id)).findFirst().orElse(null);
        if (permission != null) {
            this.permissions.remove(permission);
            permission.getAudiences().remove(this);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Signalisation getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(Signalisation signalisation) {
        this.signalisation = signalisation;
    }

    public AudienceType getAudience_type() {
        return audience_type;
    }

    public void setAudience_type(AudienceType audience_type) {
        this.audience_type = audience_type;
    }

    public Integer getAudience_id() {
        return audience_id;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
