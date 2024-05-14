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
@Table(name = "HANHKHACH")
public class HanhKhach implements java.io.Serializable{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHK")
    private int maHK;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "DinhDanh")
    private String dinhDanh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;
}
