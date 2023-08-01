package com.blood_stock_server.business.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blood_storage")
public class BloodStorageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_storage_id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<AddressEntity> addressIds;
    @JsonIgnore
    @ManyToMany(mappedBy = "bloodStorageIds")
    private Set<BloodInfoEntity> bloodInfoIds;

    public BloodStorageEntity(Long id) {
        this.id = id;
    }
}