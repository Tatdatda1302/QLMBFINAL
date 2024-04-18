package com.example.qlbvmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.qlbvmb.TableID.TrungGianID;
import java.time.LocalTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@IdClass(TrungGianID.class)
@Table(name = "TRUNGGIAN")
public class TrungGian implements java.io.Serializable{

        @Id
        @Column(name = "MaSanBayTG")
        private String maSanBayTG;

        @Id
        @Column(name = "MaChuyenBay")
        private String maChuyenBay;

        @Column(name = "ThoiGianDung")
        private LocalTime thoiGianDung;

        @Column(name = "GhiChu")
        private String ghiChu;
}
