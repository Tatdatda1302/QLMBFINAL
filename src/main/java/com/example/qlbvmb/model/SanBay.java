package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "SANBAY")
public class SanBay implements java.io.Serializable{

    @Id
    @Column(name = "MaSanBay")
    private String maSanBay;

    @Column(name = "TenSanBay")
    private String tenSanBay;
}
