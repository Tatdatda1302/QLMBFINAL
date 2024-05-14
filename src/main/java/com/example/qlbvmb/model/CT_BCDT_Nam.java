package com.example.qlbvmb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.qlbvmb.TableID.CTBCDTNID;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CTBCDTNID.class)
@Table(name = "CT_BCDT_NAM")
public class CT_BCDT_Nam {
    
    @Id
    @Column(name = "Thang")
    private int thang;
    
    @Id
    @Column(name = "Nam")
    private int nam;

    @Column(name = "SoChuyenBay")
    private int soChuyenBay;

    @Column(name = "DoanhThu")
    private double doanhThu;

    @Column(name = "TiLe")
    private double tiLe;
}

