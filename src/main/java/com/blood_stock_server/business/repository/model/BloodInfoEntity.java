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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blood_info")
public class BloodInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_info_id")
    private Long id;
    @Column(name = "date_blood_taken")
    private LocalDate bloodTakenDate;
    @Column(name = "expire_date")
    private LocalDate expireDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_group_id")
    private BloodGroupEntity bloodGroupId;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "blood_storage_id")
    private BloodStorageEntity bloodStorageId;

    public BloodInfoEntity(Long id) {
        this.id = id;
    }
}
