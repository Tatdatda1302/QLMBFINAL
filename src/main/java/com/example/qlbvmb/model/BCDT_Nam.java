package com.example.qlbvmb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BCDT_NAM")
public class BCDT_Nam {
    
    @Id
    @Column(name = "Nam")
    private int nam;

    @Column(name = "TongDoanhThu")
    private double tongDoanhThu;
}
