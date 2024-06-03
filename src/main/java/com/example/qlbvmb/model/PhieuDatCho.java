package com.example.qlbvmb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PHIEUDATCHO")
public class PhieuDatCho implements java.io.Serializable{
    
    @Id
    @Column(name = "SoPhieuDatCho")
    private String soPhieuDatCho;

    @Column(name = "NgayDat")
    private LocalDate ngayDat;

    @Column(name = "MaChuyenBay")
    private String maChuyenBay;

    @Column(name = "MaHangVe")
    private String maHangVe;

    @Column(name = "MaHK")
    private int maHK;

    @Column(name = "MaLHK")
    private String maLHK;

    @Column(name = "SoGhe")
    private int soGhe;

    @Column(name = "GiaVe")
    private double giaVe;

    @Column(name = "TinhTrang")
    private String tinhTrang;

}
