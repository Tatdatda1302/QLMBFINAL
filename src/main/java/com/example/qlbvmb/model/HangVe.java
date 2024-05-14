package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "HANGVE")
public class HangVe {
    
    @Id
    @Column(name = "MaHangVe")
    String maHangVe;
    
    @Column(name = "TenHangVe")
    String tenHangVe;

    @Column(name = "TiLeGiaHangVe")
    double tiLeGiaHangVe;
}
