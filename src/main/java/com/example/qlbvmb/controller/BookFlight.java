package com.example.qlbvmb.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.model.DSGhe;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.example.qlbvmb.model.BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.SanBay;
import com.example.qlbvmb.model.PhieuDatCho;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.model.CustomerUserDetails;
import com.example.qlbvmb.model.FlightTicket;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.time.LocalDate;
import com.example.qlbvmb.model.HanhKhach;
import com.example.qlbvmb.model.LoaiHK;
import com.example.qlbvmb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Controller
public class BookFlight {

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @GetMapping("/{role}/BookFlight/{maChuyenBay}/{HV}")
    public String showBookTicketPage(@PathVariable("role") String role, @PathVariable("maChuyenBay") String maChuyenBay, @PathVariable("HV") String maHangve, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        Iterable<LoaiHK> loaiHK = flightService.getAllLoaiHK();
        model.addAttribute("loaiHK", loaiHK);
        Chuyenbay chuyenbay = flightService.getFlightById(maChuyenBay);
        model.addAttribute("maHangve", maHangve);
        model.addAttribute("chuyenbay", chuyenbay);
        SanBay sanbaydi = flightService.getSanBayById(chuyenbay.getMaSanBayDi());
        model.addAttribute("sanbaydi", sanbaydi);
        SanBay sanbayden = flightService.getSanBayById(chuyenbay.getMaSanBayDen());
        model.addAttribute("sanbayden", sanbayden);
        Iterable<DSGhe> dsghe = flightService.getGhe(chuyenbay.getMaMB(), maHangve, chuyenbay.getMaChuyenBay());
        model.addAttribute("dsghe", dsghe);
        PhieuDatCho phieuDatCho = new PhieuDatCho();
        model.addAttribute("phieuDatCho", phieuDatCho);
        return "index_booking";
    }

    @PostMapping("/{role}/BookFlight/{maChuyenBay}/{HV}")
    public String bookTicket(@PathVariable("maChuyenBay") String maChuyenBay, 
                            @PathVariable("HV") String maHangve,
                            @PathVariable("role") String role, 
                            Model model,
                            @ModelAttribute PhieuDatCho phieuDatCho,
                            @ModelAttribute DSGhe dsghe,
                            @ModelAttribute FlightTicket flightTicket,
                            @ModelAttribute HanhKhach hanhKhach1,
                            @ModelAttribute CT_BCDT_Thang ctbct,
                            @ModelAttribute CT_BCDT_Nam ctbcn,
                            @ModelAttribute BCDT_Nam bcnam,
                            HttpServletRequest request) {

        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        String dinhDanh = request.getParameter("dinhDanh");
        if(!userService.isHanhKhachExist(dinhDanh)){
            userService.createNewHanhKhach(hanhKhach1);
        }
        HanhKhach HK = userService.getHanhKhach(dinhDanh);
        Chuyenbay chuyenbay = flightService.getFlightById(maChuyenBay);
        model.addAttribute("chuyenbay", chuyenbay);

        CtHangve ctHangve = flightService.getVe(maHangve, maChuyenBay);
        model.addAttribute("maHangve", maHangve);

        SanBay sanbaydi = flightService.getSanBayById(chuyenbay.getMaSanBayDi());
        model.addAttribute("sanbaydi", sanbaydi);
        SanBay sanbayden = flightService.getSanBayById(chuyenbay.getMaSanBayDen());
        model.addAttribute("sanbayden", sanbayden);

        LocalDate ngayDat = LocalDate.now();
        phieuDatCho.setMaHangVe(maHangve);
        phieuDatCho.setNgayDat(ngayDat); 
        phieuDatCho.setMaChuyenBay(maChuyenBay);
        phieuDatCho.setMaHK(HK.getMaHK());
        phieuDatCho.setMaLHK(request.getParameter("maLHK"));
        phieuDatCho.setSoGhe(Integer.parseInt(request.getParameter("soGhe")));
        phieuDatCho.setSoPhieuDatCho(phieuDatCho.getMaChuyenBay() + phieuDatCho.getMaHangVe() + phieuDatCho.getSoGhe());
        LoaiHK loaiHK = flightService.getLoaiHKById(phieuDatCho.getMaLHK());
        phieuDatCho.setGiaVe(loaiHK.getTiLeLHK()*ctHangve.getDonGiaHV());
        phieuDatCho.setTinhTrang("Booked");
        flightService.createPhieuDatCho(phieuDatCho);

        flightService.deleteGhe(phieuDatCho.getSoGhe(), chuyenbay.getMaMB(), maHangve, maChuyenBay);
        ctHangve.setSoGheDat(ctHangve.getSoGheDat()+1);
        ctHangve.setSoGheConLai(ctHangve.getSoGheConLai()-1);
        flightService.createCtHangve(ctHangve);

        flightTicket.setMaVe(phieuDatCho.getSoPhieuDatCho());
        flightTicket.setMaChuyenBay(maChuyenBay);
        flightTicket.setMaHangVe(maHangve);
        flightTicket.setMaHK(HK.getMaHK());
        flightTicket.setMaLHK(phieuDatCho.getMaLHK());
        flightTicket.setSoGhe(phieuDatCho.getSoGhe());
        flightTicket.setGiaVe(phieuDatCho.getGiaVe());
        flightTicket.setNgayDat(phieuDatCho.getNgayDat());
        flightService.createFlightTicket(flightTicket);

        String ngayGioBay = chuyenbay.getNgayGioBay();
        String[] parts = ngayGioBay.split("-");
        String year = parts[0];
        int nam = Integer.parseInt(year);
        String month = parts[1];
        int thang = Integer.parseInt(month);

        bcnam = flightService.getTBCNam(nam);
        bcnam.setTongDoanhThu(bcnam.getTongDoanhThu() + phieuDatCho.getGiaVe());
        flightService.createBCDTNam(bcnam);

        ctbcn = flightService.getBCNam(thang, nam);
        ctbcn.setDoanhThu(ctbcn.getDoanhThu() + phieuDatCho.getGiaVe());
        flightService.createCTBCDTNam(ctbcn);

        ctbct = flightService.getBCThang(maChuyenBay);
        ctbct.setDoanhThu(ctbct.getDoanhThu() + phieuDatCho.getGiaVe());
        if(phieuDatCho.getMaHangVe().equals("HANG1")) {
            ctbct.setSoVeHang1(ctbct.getSoVeHang1() + 1);
        }
        else {
            ctbct.setSoVeHang2(ctbct.getSoVeHang2() + 1);
        }
        flightService.createCTBCDTThang(ctbct);

        model.addAttribute("role", role);
        model.addAttribute("phieuDatCho", phieuDatCho);
        return "index_booking";
    }
    

}
