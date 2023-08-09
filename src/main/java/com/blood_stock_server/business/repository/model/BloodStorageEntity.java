package com.blood_stock_server.business.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
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
    @OneToMany(mappedBy = "bloodStorageId", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<AddressEntity> addressIds;
    @OneToMany(mappedBy = "bloodStorageId", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<BloodInfoEntity> bloodInfoIds;

    public void add(BloodInfoEntity bloodInfo) {
        if (bloodInfoIds == null) {
            bloodInfoIds = new ArrayList<>();
        }
        bloodInfoIds.add(bloodInfo);
        bloodInfo.setBloodStorageId(this);
    }

    public BloodStorageEntity(Long id) {
        this.id = id;
    }
}
