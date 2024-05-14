package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "THAMSO")
public class ThamSo {

    @Id
    @Column(name = "ID")
    int id;
    
    @Column(name = "TGBayToiThieu")
    String tgBayToiThieu;

    @Column(name = "TGDungToiThieu")
    String tgDungToiThieu;

    @Column(name = "TGDungToiDa")
    String tgDungToiDa;

    @Column(name = "SoSanBayTGToiDa")
    int soSanBayTGToiDa;

    @Column(name = "TGDatVeChamNhat")
    int tgDatVeChamNhat;

    @Column(name = "TGHuyChamNhat")
    int tgHuyChamNhat;
}
