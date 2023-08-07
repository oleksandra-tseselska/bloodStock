package com.blood_stock_server.business.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "blood_stock",
            joinColumns = @JoinColumn(name = "blood_storage_id"),
            inverseJoinColumns = @JoinColumn(name = "blood_info_id"))
    private List<BloodStorageEntity> bloodStorageIds;

    public BloodInfoEntity(Long id) {
        this.id = id;
    }
}
