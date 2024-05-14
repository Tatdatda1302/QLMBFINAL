package com.example.qlbvmb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.qlbvmb.TableID.CTBCDTTID;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CTBCDTTID.class)
@Table(name = "CT_BCDT_THANG")
public class CT_BCDT_Thang {
    
    @Id
    @Column(name = "Thang")
    private int thang;
    
    @Id
    @Column(name = "Nam")
    private int nam;

    @Id
    @Column(name = "MaChuyenBay")
    private String maChuyenBay;

    @Column(name = "SoVeHang1")
    private int soVeHang1;

    @Column(name = "SoVeHang2")
    private int soVeHang2;

    @Column(name = "DoanhThu")
    private double doanhThu;

    @Column(name = "TiLe")
    private double tiLe;
}
