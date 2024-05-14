package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "HANGMAYBAYSX")
public class HangMBSX {
    
    @Id
    @Column(name = "MaHMB")
    String maHMB;

    @Column(name = "TenHMB")
    String tenHMB;
}
