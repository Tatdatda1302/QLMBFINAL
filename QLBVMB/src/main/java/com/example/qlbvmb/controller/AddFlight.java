package com.example.qlbvmb.controller;

import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/home")
public class AddFlight {

    @Autowired
    FlightService flightService;

    @PostMapping("/add_flight")
    public ResponseEntity<String> register(@ModelAttribute Chuyenbay chuyenbay, 
                                            @ModelAttribute TrungGian trunggian, 
                                            @ModelAttribute CtHangve cthangve) {
        
        ResponseEntity<String> response = null;

        try {
            if(flightService.isFlightExist(chuyenbay.getMaChuyenBay())) {
                response = ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Flight already exists for flight = " + chuyenbay.getMaChuyenBay());
            }
            else {
                if(flightService.isSanBayExist(chuyenbay.getMaSanBayDi()) & flightService.isSanBayExist(chuyenbay.getMaSanBayDen())) {
                    flightService.createChuyenbay(chuyenbay);

                    cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    cthangve.setMaHangve("HANG1");
                    flightService.createCtHangve(cthangve);

                    cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    cthangve.setMaHangve("HANG2");
                    flightService.createCtHangve(cthangve);
                    
                    if(trunggian.getMaSanBayTG() != null && trunggian.getThoiGianDung() != null) {
                        trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                        flightService.createTrunggian(trunggian);
                    }
                    response = ResponseEntity.status(HttpStatus.CREATED)
                            .body("Flight is created successfully for flight = " + chuyenbay.getMaChuyenBay());
                }
                else {
                    response = ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Sanbay does not exist for flight = " + chuyenbay.getMaChuyenBay());
                }
            }
        } catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred from server with exception = " + exception.getMessage());
        }
        return response;
    }
}
