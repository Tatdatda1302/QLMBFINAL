package com.example.qlbvmb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MAYBAY")
public class Maybay implements java.io.Serializable{
    
    @Id
    @Column(name = "MaMB")
    private String maMB;

    @Column(name = "TenMB")
    private String tenMB;

    @Column(name = "MaHMB")
    private String maHMB;

    @Column(name = "SLGhe")
    private int slGhe;
}
