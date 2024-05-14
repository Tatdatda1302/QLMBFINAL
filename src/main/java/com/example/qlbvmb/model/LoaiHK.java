package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "LOAIHANHKHACH")
public class LoaiHK {
    
    @Id
    @Column(name = "MaLHK")
    String maLHK;

    @Column(name = "TenLHK")
    String tenLHK;

    @Column(name = "TiLeLHK")
    double tiLeLHK;
}
