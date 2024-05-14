package com.example.qlbvmb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHUYENBAY")
public class Chuyenbay implements java.io.Serializable{
    
    @Id
    @Column(name = "MaChuyenBay")
    private String maChuyenBay;

    @Column(name = "MaMB")
    private String maMB;

    @Column(name = "MaSanBayDi")
    private String maSanBayDi;

    @Column(name = "MaSanBayDen")
    private String maSanBayDen;

    @Column(name = "NgayGioBay")
    private String ngayGioBay;

    @Column(name = "ThoiGianBay")
    private String thoiGianBay;

    @Column(name = "DonGia")
    private float donGia;
}