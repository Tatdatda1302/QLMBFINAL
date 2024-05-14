package com.example.qlbvmb.controller;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.SanBay;
import com.example.qlbvmb.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.model.FlightWithCTHV;


@Controller
public class GetFlight {

    @Autowired
    FlightService flightService;


    @GetMapping("/{role}/SearchFlight")
    public String showBookTicketPage(@PathVariable("role") String role, Model model) {
        Iterable<SanBay> sanbay = flightService.getAllSanbay();
        model.addAttribute("role", role);
        model.addAttribute("sanbay", sanbay);
        return "index_search";
    }

    @PostMapping("/{role}/SearchFlight")
    public String searchFlight(@PathVariable("role") String role, HttpServletRequest request, Model model) {
        model.addAttribute("role", role);
        String maSanBayDi = request.getParameter("maSanBayDi");
        String maSanBayDen = request.getParameter("maSanBayDen");
        String ngayKhoiHanhStr = request.getParameter("ngayKhoiHanh");
        String maHangVe = request.getParameter("maHangVe");

        LocalDate ngayKhoiHanh = LocalDate.parse(ngayKhoiHanhStr);

        Iterable<Chuyenbay> chuyenbay = flightService.getFlight(maSanBayDi, maSanBayDen, ngayKhoiHanh);

        List<FlightWithCTHV> flightWithCTHV = new ArrayList<>();
        for(Chuyenbay flight : chuyenbay) {
            String maChuyenBay = flight.getMaChuyenBay();
            Iterable<CtHangve> ctHangve = flightService.getCtHangVe(maHangVe, maChuyenBay);
            for (CtHangve cthangve : ctHangve) {
                flightWithCTHV.add(new FlightWithCTHV(flight, cthangve));
            }
        }
        model.addAttribute("flightWithCTHV", flightWithCTHV);
        return "index_search";
    }
}
