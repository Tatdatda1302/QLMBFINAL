package com.example.qlbvmb.controller;

import com.example.qlbvmb.model.BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.model.DSGhe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import org.springframework.ui.Model;
import com.example.qlbvmb.model.Maybay;
import com.example.qlbvmb.model.SanBay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AddFlight {

    @Autowired
    FlightService flightService;

    @GetMapping ("{role}/add_flight")
    public String showAddFlightPage(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        Iterable<Maybay> maybay = flightService.getAllMaybay();
        model.addAttribute("maybay", maybay);

        Iterable<SanBay> sanbay = flightService.getAllSanbay();
        model.addAttribute("sanbay", sanbay);

        return "index_adding";
    }
    @PostMapping("{role}/add_flight")
    public String register(HttpServletRequest request,
                            Model model,
                            @ModelAttribute Chuyenbay chuyenbay, 
                            @ModelAttribute TrungGian trunggian, 
                            @ModelAttribute CtHangve cthangve,
                            @ModelAttribute DSGhe dsghe,
                            @ModelAttribute CT_BCDT_Thang ctbct,
                            @ModelAttribute CT_BCDT_Nam ctbcn,
                            @ModelAttribute BCDT_Nam bcnam,
                            @PathVariable("role") String role){

        String response;
        model.addAttribute("role", role);

        try {
            if(flightService.isFlightExist(chuyenbay.getMaChuyenBay())) {
                response = "Flight is already existed for flight " + chuyenbay.getMaChuyenBay();
            }
            else {
                flightService.createChuyenbay(chuyenbay);

                cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                cthangve.setMaHangve("HANG1");
                String SoLuong1 = request.getParameter("soLuong1");
                int soLuong1 = Integer.parseInt(SoLuong1);
                cthangve.setSoLuong(soLuong1);
                cthangve.setSoGheDat(0);
                cthangve.setSoGheBan(0);
                cthangve.setSoGheConLai(soLuong1);
                cthangve.setDonGiaHV(1.05*chuyenbay.getDonGia());
                flightService.createCtHangve(cthangve);

                for(int i=1;i<=soLuong1;i++) {
                    dsghe.setSoGhe(i);
                    dsghe.setMaMB(chuyenbay.getMaMB());
                    dsghe.setMaHangVe("HANG1");
                    dsghe.setGhiChu(chuyenbay.getMaChuyenBay());
                    flightService.createDSGhe(dsghe);
                }

                cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                cthangve.setMaHangve("HANG2");
                String SoLuong2 = request.getParameter("soLuong2");
                int soLuong2 = Integer.parseInt(SoLuong2);
                cthangve.setSoLuong(soLuong2);
                cthangve.setSoGheDat(0);
                cthangve.setSoGheBan(0);
                cthangve.setSoGheConLai(soLuong2);
                cthangve.setDonGiaHV(chuyenbay.getDonGia());
                flightService.createCtHangve(cthangve);
                
                for(int i=1+soLuong1;i<=soLuong1+soLuong2;i++) {
                    dsghe.setSoGhe(i);
                    dsghe.setMaMB(chuyenbay.getMaMB());
                    dsghe.setMaHangVe("HANG2");
                    dsghe.setGhiChu(chuyenbay.getMaChuyenBay());
                    flightService.createDSGhe(dsghe);
                }

                String MaSanBayTG1 = request.getParameter("maSanBayTG1");
                String ThoiGianDung1 = request.getParameter("thoiGianDung1");
                if(MaSanBayTG1 != null && ThoiGianDung1 != null && !ThoiGianDung1.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG1);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung1));
                    String GhiChu1 = request.getParameter("ghiChu1");
                    trunggian.setGhiChu(GhiChu1);   
                    flightService.createTrunggian(trunggian);
                }

                String MaSanBayTG2 = request.getParameter("maSanBayTG2");
                String ThoiGianDung2 = request.getParameter("thoiGianDung2");
                if(MaSanBayTG2 != null && ThoiGianDung2 != null && !ThoiGianDung2.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG2);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung2));
                    String GhiChu2 = request.getParameter("ghiChu2");
                    trunggian.setGhiChu(GhiChu2);   
                    flightService.createTrunggian(trunggian);
                }

                String ngayGioBay = chuyenbay.getNgayGioBay();
                String[] parts = ngayGioBay.split("-");
                String year = parts[0];
                int nam = Integer.parseInt(year);
                String month = parts[1];
                int thang = Integer.parseInt(month);

                bcnam.setNam(nam);
                bcnam.setTongDoanhThu(0);
                flightService.createBCDTNam(bcnam);

                ctbcn.setThang(thang);
                ctbcn.setNam(nam);
                ctbcn.setSoChuyenBay(ctbcn.getSoChuyenBay()+1);
                ctbcn.setDoanhThu(0);
                ctbcn.setTiLe(0);
                flightService.createCTBCDTNam(ctbcn);
                
                ctbct.setThang(thang);
                ctbct.setNam(nam);
                ctbct.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                ctbct.setSoVeHang1(0);
                ctbct.setSoVeHang2(0);
                ctbct.setDoanhThu(0);
                ctbct.setTiLe(0);
                flightService.createCTBCDTThang(ctbct);


                response = "Flight is added successfully for flight " + chuyenbay.getMaChuyenBay();

                Iterable<Chuyenbay> flightInfo = flightService.getAllChuyenbay();
                model.addAttribute("flightInfo", flightInfo);
            }
        } catch (Exception exception) {
            response = "Flight is not added for flight " + chuyenbay.getMaChuyenBay();
        }
        model.addAttribute("response", response);
        return "announcement";
    }
}
