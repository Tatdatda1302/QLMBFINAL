package com.example.qlbvmb.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightWithCTHV {
    private Chuyenbay flight;
    private CtHangve cthangve;
    private long TgDatVeChamNhat;
}
