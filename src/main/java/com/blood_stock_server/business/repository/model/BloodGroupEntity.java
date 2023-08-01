package com.blood_stock_server.business.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blood_group")
public class BloodGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_group_id")
    private Long id;
    @Column(name = "blood_group")
    private String group;
    @JsonIgnore
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<BloodInfoEntity> bloodInfoIds;

    public BloodGroupEntity(Long id) {
        this.id = id;
    }
}
