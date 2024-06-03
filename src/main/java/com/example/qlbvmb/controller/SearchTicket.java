package com.example.qlbvmb.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.service.UserService;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.model.CustomerUserDetails;
import com.example.qlbvmb.model.DSGhe;
import com.example.qlbvmb.model.FlightTicket;
import com.example.qlbvmb.model.FlightWithTicket;
import com.example.qlbvmb.model.HanhKhach;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.qlbvmb.model.PhieuDatCho;
import com.example.qlbvmb.model.ThamSo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;





@Controller
public class SearchTicket {

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender emailSender;
    
    @GetMapping("/{role}/FlightTicket")
    public String FindTicket(@PathVariable("role") String role, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        model.addAttribute("role", role);
        Iterable<HanhKhach> hanhKhach = userService.getAllHanhKhach();
        model.addAttribute("hanhKhach", hanhKhach);
        return "index_ticket";
    }
    
    @PostMapping("/{role}/FlightTicket")
    public String searchTicket(@PathVariable("role") String role, Model model, HttpServletRequest request) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        String maHK = request.getParameter("maHK");
        model.addAttribute("role", role);
        Iterable<HanhKhach> hanhKhach = userService.getAllHanhKhach();
        model.addAttribute("hanhKhach", hanhKhach);
        return "redirect:/" + "STAFF" + "/FlightTicket/" + maHK;
    }

    @GetMapping("/{role}/FlightTicket/{maHK}")
    public String showTicketPage(@PathVariable("role") String role, @PathVariable("maHK") int maHK, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        model.addAttribute("role", role);
        Iterable<FlightTicket> flightTicket = flightService.getFlightTicket(maHK);
        Chuyenbay chuyenbay;
        List<FlightWithTicket> CB = new ArrayList<>();
        for(FlightTicket ticket : flightTicket) {
            chuyenbay = flightService.getFlightById(ticket.getMaChuyenBay());
            CB.add(new FlightWithTicket(chuyenbay, ticket));
        }
        model.addAttribute("CB", CB);
        model.addAttribute("flightTicket", flightTicket);
        Iterable<HanhKhach> hanhKhach = userService.getAllHanhKhach();
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("maHK", maHK);
        if(role.equals("USER")) {
            return "index_ticket_user";
        }
        return "index_ticket";
    }

    @GetMapping("/{role}/FlightTicket/{maHK}/{maVe}")
    public String xuatVe(@PathVariable("maHK") int maHK, @PathVariable("maVe") String maVe, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        Iterable<HanhKhach> hanhKhach = userService.getAllHanhKhach();
        model.addAttribute("hanhKhach", hanhKhach);
        FlightTicket flightTicket = flightService.getFlightTicketById(maVe, maHK);
        LocalDate ngayXuatVe = LocalDate.now();
        flightTicket.setNgayXuatVe(ngayXuatVe);
        PhieuDatCho phieuDatCho = flightService.getPhieuDatCho(maVe, maHK);
        phieuDatCho.setTinhTrang("Issued");
        CtHangve ctHangve = flightService.getVe(flightTicket.getMaHangVe(), flightTicket.getMaChuyenBay());
        ctHangve.setSoGheBan(ctHangve.getSoGheBan() + 1);
        flightService.createCtHangve(ctHangve);
        flightService.createPhieuDatCho(phieuDatCho);
        flightService.createFlightTicket(flightTicket);

        HanhKhach hanhKhach1 = userService.getHanhKhachById(maHK);
        String email = hanhKhach1.getEmail();
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("viethubairline@gmail.com");
        message.setTo(email);
        message.setSubject("Your Flight Ticket");
        
        String flightInfo = String.format(
        "Dear %s,\n\nHere's your flight ticket information:\n\nFlight ID: %s\nTicket ID: %s\nPassenger ID: %s\nLHK ID: %s\nSeat Number: %s\n\nThank you for choosing our airline!",
        hanhKhach1.getHoTen(),
        flightTicket.getMaChuyenBay(),
        flightTicket.getMaVe(),
        flightTicket.getMaHK(),
        flightTicket.getMaLHK(),
        flightTicket.getSoGhe()
        );
        message.setText(flightInfo);

        // send the email
        emailSender.send(message);
        return "redirect:/" + "STAFF" + "/FlightTicket/" + maHK;
    }

    @GetMapping("/{role}/PhieuDatCho/{maHK}")
    public String showPhieuDatChoUser(@PathVariable("role") String role, @PathVariable("maHK") int maHK, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("customer", customer);
        model.addAttribute("role", role);
        ThamSo thamso = flightService.getThamSo();
        model.addAttribute("thamso", thamso);
        Iterable<PhieuDatCho> phieuDatCho = flightService.getPhieuDatChoById(maHK);

        for(PhieuDatCho phieu : phieuDatCho) {
            Chuyenbay flight = flightService.getFlightById(phieu.getMaChuyenBay());
            String ngayGioBay = flight.getNgayGioBay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ngayGioBayLocal = LocalDateTime.parse(ngayGioBay, formatter);
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(now, ngayGioBayLocal);
            long TgHuyVeChamNhat = duration.toHours();
            System.out.println(TgHuyVeChamNhat);
            if(phieu.getTinhTrang().equals("Issued") || phieu.getTinhTrang().equals("Cancelled")) {
                continue;
            } else if(TgHuyVeChamNhat < thamso.getTgHuyChamNhat()) {
                phieu.setTinhTrang("Unable be cancelled");
            } else {
                phieu.setTinhTrang("Booked");
            }
        }
        model.addAttribute("phieuDatCho", phieuDatCho);
        return "index_phieudatcho";
    }

    @GetMapping("/{role}/PhieuDatCho")
    public String showPhieuDatChoStaff(@PathVariable("role") String role, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("role", role);
        LocalDate ngayKhoiHanh = LocalDate.now();
        Iterable<Chuyenbay> chuyenbay = flightService.getFlightByNgayGioBay(ngayKhoiHanh);
        List<PhieuDatCho> phieuDatCho = new ArrayList<>();
        for(Chuyenbay flight : chuyenbay) {
            String maChuyenBay = flight.getMaChuyenBay();
            flightService.updateByNgayDatVe(maChuyenBay);
            Iterable<PhieuDatCho> phieuDatChoByFlight = flightService.getPhieuDatChoByMaChuyenBay(maChuyenBay);
            for(PhieuDatCho phieu : phieuDatChoByFlight) {
                phieuDatCho.add(phieu);
            }
        }
        model.addAttribute("phieuDatCho", phieuDatCho);
        return "index_phieudatcho";
    }

    @GetMapping("/{role}/PhieuDatCho/{maHK}/delete/{id}")
    public String deletePhieuDatCho(@PathVariable("role") String role, @PathVariable("id") String id, @PathVariable("maHK") int maHK, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        PhieuDatCho phieuDatCho = flightService.getPhieuDatCho(id, maHK);
        phieuDatCho.setTinhTrang("Cancelled");

        flightService.deleteTicket(phieuDatCho.getSoPhieuDatCho());

        CtHangve ctHangve = flightService.getVe(phieuDatCho.getMaHangVe(), phieuDatCho.getMaChuyenBay());
        ctHangve.setSoGheConLai(ctHangve.getSoGheConLai() + 1);
        ctHangve.setSoGheDat(ctHangve.getSoGheDat() - 1);
        if(phieuDatCho.getTinhTrang().equals("Issued")){
            ctHangve.setSoGheBan(ctHangve.getSoGheBan() - 1);
        }
        flightService.createCtHangve(ctHangve);

        Chuyenbay chuyenbay = flightService.getFlightById(phieuDatCho.getMaChuyenBay());
        DSGhe dsghe = new DSGhe();
        dsghe.setSoGhe(phieuDatCho.getSoGhe());
        dsghe.setMaMB(chuyenbay.getMaMB());
        dsghe.setMaHangVe(phieuDatCho.getMaHangVe());
        dsghe.setGhiChu(phieuDatCho.getMaChuyenBay());
        flightService.createDSGhe(dsghe);

        flightService.createPhieuDatCho(phieuDatCho);
        return "redirect:/{role}/PhieuDatCho/{maHK}";
    }
}
