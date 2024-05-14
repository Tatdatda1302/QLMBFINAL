package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.qlbvmb.TableID.CTHVID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@IdClass(CTHVID.class)
@Table(name = "CT_HANGVE")
public class CtHangve implements java.io.Serializable{

    @Id
    @Column(name = "MaHangve")
    private String maHangve;

    @Id
    @Column(name = "MaChuyenBay")
    private String maChuyenBay;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "SoGheDat")
    private int soGheDat;

    @Column(name = "SoGheBan")
    private int soGheBan;

    @Column(name = "SoGheConLai")
    private int soGheConLai;

    @Column(name = "DonGiaHV")
    private double donGiaHV;
}
