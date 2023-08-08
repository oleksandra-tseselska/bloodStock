package com.blood_stock_server.business.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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
    private List<AddressEntity> addressIds;
    @OneToMany(mappedBy = "bloodStorageId", fetch = FetchType.LAZY)
    private List<BloodInfoEntity> bloodInfoIds;

    public BloodStorageEntity(Long id) {
        this.id = id;
    }
}
