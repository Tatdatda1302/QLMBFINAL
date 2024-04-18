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
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalTime;

@RestController
@RequestMapping("/home")
public class AddFlight {

    @Autowired
    FlightService flightService;

    @PostMapping("/add_flight")
    public ResponseEntity<String> register(HttpServletRequest request,
                                            @ModelAttribute Chuyenbay chuyenbay, 
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
                    String SoLuong1 = request.getParameter("soLuong1");
                    cthangve.setSoLuong(Integer.parseInt(SoLuong1));
                    flightService.createCtHangve(cthangve);

                    cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    cthangve.setMaHangve("HANG2");
                    String SoLuong2 = request.getParameter("soLuong2");
                    cthangve.setSoLuong(Integer.parseInt(SoLuong2));
                    flightService.createCtHangve(cthangve);
                    
                    String MaSanBayTG1 = request.getParameter("maSanBayTG1");
                    System.out.println(MaSanBayTG1);
                    String ThoiGianDung1 = request.getParameter("thoiGianDung1");
                    if(MaSanBayTG1 != null && ThoiGianDung1 != null) {
                        trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                        trunggian.setMaSanBayTG(MaSanBayTG1);
                        trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung1));
                        String GhiChu1 = request.getParameter("ghiChu1");
                        trunggian.setGhiChu(GhiChu1);   
                        flightService.createTrunggian(trunggian);
                    }

                    String MaSanBayTG2 = request.getParameter("maSanBayTG2");
                    System.out.println(MaSanBayTG2);
                    String ThoiGianDung2 = request.getParameter("thoiGianDung2");
                    if(MaSanBayTG2 != null && ThoiGianDung2 != null) {
                        trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                        trunggian.setMaSanBayTG(MaSanBayTG2);
                        trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung2));
                        String GhiChu2 = request.getParameter("ghiChu2");
                        trunggian.setGhiChu(GhiChu2);   
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
