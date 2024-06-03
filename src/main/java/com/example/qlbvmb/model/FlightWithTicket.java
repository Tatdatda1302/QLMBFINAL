package com.example.qlbvmb.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightWithTicket {
    private Chuyenbay chuyenbay;
    private FlightTicket ticket;
}
