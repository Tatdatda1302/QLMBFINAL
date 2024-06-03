package com.example.qlbvmb.controller;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.SanBay;
import com.example.qlbvmb.model.ThamSo;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.model.CustomerUserDetails;
import com.example.qlbvmb.model.FlightWithCTHV;
import com.example.qlbvmb.model.HangVe;
import com.example.qlbvmb.model.HanhKhach;
import java.time.LocalDateTime;



@Controller
public class GetFlight {

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @GetMapping("/{role}/SearchFlight")
    public String showBookTicketPage(@PathVariable("role") String role, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        Iterable<SanBay> sanbay = flightService.getAllSanbay();
        Iterable<HangVe> hangve = flightService.getAllHangVe();
        model.addAttribute("role", role);
        model.addAttribute("sanbay", sanbay);
        model.addAttribute("hangve", hangve);
        return "index_search";
    }

    @PostMapping("/{role}/SearchFlight")
    public String searchFlight(@PathVariable("role") String role, HttpServletRequest request, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        String maSanBayDi = request.getParameter("maSanBayDi");
        String maSanBayDen = request.getParameter("maSanBayDen");
        String ngayKhoiHanhStr = request.getParameter("ngayKhoiHanh");
        String maHangVe = request.getParameter("maHangVe");
        ThamSo thamso = flightService.getThamSo();
        model.addAttribute("thamso", thamso);

        LocalDate ngayKhoiHanh = LocalDate.parse(ngayKhoiHanhStr);

        Iterable<Chuyenbay> chuyenbay = flightService.getFlight(maSanBayDi, maSanBayDen, ngayKhoiHanh);
        List<TrungGian> trunggians = new ArrayList<>();

        List<FlightWithCTHV> flightWithCTHV = new ArrayList<>();
        for(Chuyenbay flight : chuyenbay) {
            String ngayGioBay = flight.getNgayGioBay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ngayGioBayLocal = LocalDateTime.parse(ngayGioBay, formatter);
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(now, ngayGioBayLocal);
            long TgDatVeChamNhat = duration.toHours();

            String maChuyenBay = flight.getMaChuyenBay();
            Iterable<CtHangve> ctHangve = flightService.getCtHangVe(maHangVe, maChuyenBay);
            Iterable<TrungGian> trungGian = flightService.getTrungGianByMaChuyenBay(maChuyenBay);
            trunggians.addAll(StreamSupport.stream(trungGian.spliterator(), false).collect(Collectors.toList()));
            for (CtHangve cthangve : ctHangve) {
                flightWithCTHV.add(new FlightWithCTHV(flight, cthangve, TgDatVeChamNhat));
            }
        }
        Iterable<SanBay> sanbay = flightService.getAllSanbay();
        Iterable<HangVe> hangve = flightService.getAllHangVe();
        model.addAttribute("sanbay", sanbay);
        model.addAttribute("hangve", hangve);
        model.addAttribute("flightWithCTHV", flightWithCTHV);
        model.addAttribute("trunggians", trunggians);
        return "index_search";
    }
}
