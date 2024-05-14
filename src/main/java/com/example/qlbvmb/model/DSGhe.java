package com.example.qlbvmb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.qlbvmb.TableID.GheID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(GheID.class)
@Table(name = "DSGHE")
public class DSGhe implements java.io.Serializable{
    
    @Id
    @Column(name = "SoGhe")
    private int soGhe;

    @Id
    @Column(name = "MaMB")
    private String maMB;

    @Column(name = "MaHangVe")
    private String maHangVe;

    @Id
    @Column(name = "GhiChu")
    private String ghiChu;
}
