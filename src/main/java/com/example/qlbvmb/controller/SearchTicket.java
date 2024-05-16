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
import com.example.qlbvmb.model.DSGhe;
import com.example.qlbvmb.model.FlightTicket;
import com.example.qlbvmb.model.HanhKhach;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import com.example.qlbvmb.model.PhieuDatCho;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;




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
        model.addAttribute("role", role);
        Iterable<HanhKhach> hanhKhach = userService.getAllHanhKhach();
        model.addAttribute("hanhKhach", hanhKhach);
        return "index_ticket";
    }
    
    @PostMapping("/{role}/FlightTicket")
    public String searchTicket(@PathVariable("role") String role, Model model, HttpServletRequest request) {
        String maHK = request.getParameter("maHK");
        model.addAttribute("role", role);
        return "redirect:/" + "STAFF" + "/FlightTicket/" + maHK;
    }

    @GetMapping("/{role}/FlightTicket/{maHK}")
    public String showTicketPage(@PathVariable("role") String role, @PathVariable("maHK") int maHK, Model model) {
        model.addAttribute("role", role);
        Iterable<FlightTicket> flightTicket = flightService.getFlightTicket(maHK);
        model.addAttribute("flightTicket", flightTicket);
        return "index_ticket";
    }

    @GetMapping("/{role}/FlightTicket/{maHK}/{maVe}")
    public String xuatVe(@PathVariable("maHK") int maHK, @PathVariable("maVe") String maVe, Model model) {
        FlightTicket flightTicket = flightService.getFlightTicketById(maVe, maHK);
        LocalDate ngayXuatVe = LocalDate.now();
        flightTicket.setNgayXuatVe(ngayXuatVe);
        PhieuDatCho phieuDatCho = flightService.getPhieuDatCho(maVe, maHK);
        phieuDatCho.setTinhTrang("Đã xuất vé");
        CtHangve ctHangve = flightService.getVe(flightTicket.getMaHangVe(), flightTicket.getMaChuyenBay());
        ctHangve.setSoGheBan(ctHangve.getSoGheBan() + 1);
        flightService.createCtHangve(ctHangve);
        flightService.createPhieuDatCho(phieuDatCho);
        flightService.createFlightTicket(flightTicket);

        HanhKhach hanhKhach = userService.getHanhKhachById(maHK);
        String email = hanhKhach.getEmail();
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("viethubairline@gmail.com");
        message.setTo(email);
        message.setSubject("Your Flight Ticket");
        
        String flightInfo = String.format(
        "Dear %s,\n\nHere's your flight ticket information:\n\nFlight ID: %s\nTicket ID: %s\nPassenger ID: %s\nLHK ID: %s\nSeat Number: %s\n\nThank you for choosing our airline!",
        hanhKhach.getHoTen(),
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
        model.addAttribute("role", role);
        Iterable<PhieuDatCho> phieuDatCho = flightService.getPhieuDatChoById(maHK);
        model.addAttribute("phieuDatCho", phieuDatCho);
        return "index_phieudatcho";
    }

    @GetMapping("/{role}/PhieuDatCho")
    public String showPhieuDatChoStaff(@PathVariable("role") String role, Model model) {
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
        PhieuDatCho phieuDatCho = flightService.getPhieuDatCho(id, maHK);
        phieuDatCho.setTinhTrang("Bị hủy");

        flightService.deleteTicket(phieuDatCho.getSoPhieuDatCho());

        CtHangve ctHangve = flightService.getVe(phieuDatCho.getMaHangVe(), phieuDatCho.getMaChuyenBay());
        ctHangve.setSoGheConLai(ctHangve.getSoGheConLai() + 1);
        ctHangve.setSoGheDat(ctHangve.getSoGheDat() - 1);
        if(phieuDatCho.getTinhTrang().equals("Đã xuất vé")){
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
