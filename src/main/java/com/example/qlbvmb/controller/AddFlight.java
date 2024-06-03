package com.example.qlbvmb.controller;

import com.example.qlbvmb.model.BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.model.CustomerUserDetails;
import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.model.DSGhe;
import com.example.qlbvmb.model.HangVe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.example.qlbvmb.model.ThamSo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.qlbvmb.service.UserService;


@Controller
public class AddFlight {

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @GetMapping ("{role}/add_flight")
    public String showAddFlightPage(@PathVariable("role") String role, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        model.addAttribute("role", role);
        Iterable<Maybay> maybay = flightService.getAllMaybay();
        model.addAttribute("maybay", maybay);

        Iterable<SanBay> sanbay = flightService.getAllSanbay();
        model.addAttribute("sanbay", sanbay);

        ThamSo thamso = flightService.getThamSo();
        model.addAttribute("thamso", thamso);

        Iterable<HangVe> hangve = flightService.getAllHangVe();
        int count = 0;
        for (HangVe hv : hangve) {
            count++;
        }
        model.addAttribute("hangve", hangve);
        model.addAttribute("count", count);

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

        String response = "";
        model.addAttribute("role", role);
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);

        ThamSo thamso = flightService.getThamSo();
        model.addAttribute("thamso", thamso);

        Iterable<HangVe> hangve = flightService.getAllHangVe();
        int count = 0;
        for (HangVe hv : hangve) {
            count++;
        }
        String maSanBayDi = request.getParameter("maSanBayDi");
        String maSanBayDen = request.getParameter("maSanBayDen");
        String MaSanBayTG1 = request.getParameter("maSanBayTG1");
        String MaSanBayTG2 = request.getParameter("maSanBayTG2");
        String MaSanBayTG3 = request.getParameter("maSanBayTG3");
        String MaSanBayTG4 = request.getParameter("maSanBayTG4");
        try {
            if(flightService.isFlightExist(chuyenbay.getMaChuyenBay())) {
                response = "Flight is already existed for flight " + chuyenbay.getMaChuyenBay();
            }
            else if(maSanBayDi.equals(maSanBayDen)) {
                response = "The departure airport and the destination airport must be different";
            }
            else if (MaSanBayTG1 != null && MaSanBayTG2 != null && MaSanBayTG3 != null && MaSanBayTG4 != null) {
                if(MaSanBayTG1.equals(maSanBayDi) || MaSanBayTG1.equals(maSanBayDen) || MaSanBayTG2.equals(maSanBayDi) || MaSanBayTG2.equals(maSanBayDen) || MaSanBayTG3.equals(maSanBayDi) || MaSanBayTG3.equals(maSanBayDen) || MaSanBayTG4.equals(maSanBayDi) || MaSanBayTG4.equals(maSanBayDen)) {
                    response = "The transit airport must be different from the departure airport and the destination airport";
                }
                else if(MaSanBayTG1.equals(MaSanBayTG2) || MaSanBayTG1.equals(MaSanBayTG3) || MaSanBayTG1.equals(MaSanBayTG4) || MaSanBayTG2.equals(MaSanBayTG3) || MaSanBayTG2.equals(MaSanBayTG4) || MaSanBayTG3.equals(MaSanBayTG4)) {
                    response = "The transit airports must be different";
                }
            }
            else {
                flightService.createChuyenbay(chuyenbay);

                if(count >= 1) {
                    cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    cthangve.setMaHangve("HANG1");
                    String SoLuong1 = request.getParameter("soLuong1");
                    int soLuong1 = Integer.parseInt(SoLuong1);
                    cthangve.setSoLuong(soLuong1);
                    cthangve.setSoGheDat(0);
                    cthangve.setSoGheBan(0);
                    cthangve.setSoGheConLai(soLuong1);
                    HangVe hangve1 = flightService.getHangVeById("HANG1");
                    cthangve.setDonGiaHV(hangve1.getTiLeGiaHangVe()*chuyenbay.getDonGia());
                    flightService.createCtHangve(cthangve);

                    for(int i=1;i<=soLuong1;i++) {
                        dsghe.setSoGhe(i);
                        dsghe.setMaMB(chuyenbay.getMaMB());
                        dsghe.setMaHangVe("HANG1");
                        dsghe.setGhiChu(chuyenbay.getMaChuyenBay());
                        flightService.createDSGhe(dsghe);
                    }

                    if(count >= 2) {
                        cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                        cthangve.setMaHangve("HANG2");
                        String SoLuong2 = request.getParameter("soLuong2");
                        int soLuong2 = Integer.parseInt(SoLuong2);
                        cthangve.setSoLuong(soLuong2);
                        cthangve.setSoGheDat(0);
                        cthangve.setSoGheBan(0);
                        cthangve.setSoGheConLai(soLuong2);
                        HangVe hangve2 = flightService.getHangVeById("HANG2");
                        cthangve.setDonGiaHV(hangve2.getTiLeGiaHangVe()*chuyenbay.getDonGia());
                        flightService.createCtHangve(cthangve);
                        
                        for(int i=1+soLuong1;i<=soLuong1+soLuong2;i++) {
                            dsghe.setSoGhe(i);
                            dsghe.setMaMB(chuyenbay.getMaMB());
                            dsghe.setMaHangVe("HANG2");
                            dsghe.setGhiChu(chuyenbay.getMaChuyenBay());
                            flightService.createDSGhe(dsghe);
                        }

                        if(count >= 3) {
                            cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                            cthangve.setMaHangve("HANG3");
                            String SoLuong3 = request.getParameter("soLuong3");
                            int soLuong3 = Integer.parseInt(SoLuong3);
                            cthangve.setSoLuong(soLuong3);
                            cthangve.setSoGheDat(0);
                            cthangve.setSoGheBan(0);
                            cthangve.setSoGheConLai(soLuong3);
                            HangVe hangve3 = flightService.getHangVeById("HANG3");
                            cthangve.setDonGiaHV(hangve3.getTiLeGiaHangVe()*chuyenbay.getDonGia());
                            flightService.createCtHangve(cthangve);

                            for(int i=1+soLuong1+soLuong2;i<=soLuong1+soLuong2+soLuong3;i++) {
                                dsghe.setSoGhe(i);
                                dsghe.setMaMB(chuyenbay.getMaMB());
                                dsghe.setMaHangVe("HANG3");
                                dsghe.setGhiChu(chuyenbay.getMaChuyenBay());
                                flightService.createDSGhe(dsghe);
                            }
                            if(count >= 4) {
                                cthangve.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                                cthangve.setMaHangve("HANG4");
                                String SoLuong4 = request.getParameter("soLuong4");
                                int soLuong4 = Integer.parseInt(SoLuong4);
                                cthangve.setSoLuong(soLuong4);
                                cthangve.setSoGheDat(0);
                                cthangve.setSoGheBan(0);
                                cthangve.setSoGheConLai(soLuong4);
                                HangVe hangve4 = flightService.getHangVeById("HANG4");
                                cthangve.setDonGiaHV(hangve4.getTiLeGiaHangVe()*chuyenbay.getDonGia());
                                flightService.createCtHangve(cthangve);

                                for(int i=1+soLuong1+soLuong2+soLuong3;i<=soLuong1+soLuong2+soLuong3+soLuong4;i++) {
                                    dsghe.setSoGhe(i);
                                    dsghe.setMaMB(chuyenbay.getMaMB());
                                    dsghe.setMaHangVe("HANG4");
                                }
                            }
                        }
                    }
                }

                String ThoiGianDung1 = request.getParameter("thoiGianDung1");
                if(MaSanBayTG1 != null && ThoiGianDung1 != null && !ThoiGianDung1.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG1);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung1));
                    String GhiChu1 = request.getParameter("ghiChu1");
                    trunggian.setGhiChu(GhiChu1);   
                    flightService.createTrunggian(trunggian);
                }

                String ThoiGianDung2 = request.getParameter("thoiGianDung2");
                if(MaSanBayTG2 != null && ThoiGianDung2 != null && !ThoiGianDung2.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG2);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung2));
                    String GhiChu2 = request.getParameter("ghiChu2");
                    trunggian.setGhiChu(GhiChu2);   
                    flightService.createTrunggian(trunggian);
                }

                String ThoiGianDung3 = request.getParameter("thoiGianDung3");
                if(MaSanBayTG3 != null && ThoiGianDung3 != null && !ThoiGianDung3.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG3);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung3));
                    String GhiChu3 = request.getParameter("ghiChu3");
                    trunggian.setGhiChu(GhiChu3);   
                    flightService.createTrunggian(trunggian);
                }

                String ThoiGianDung4 = request.getParameter("thoiGianDung4");
                if(MaSanBayTG4 != null && ThoiGianDung4 != null && !ThoiGianDung4.isEmpty()) {
                    trunggian.setMaChuyenBay(chuyenbay.getMaChuyenBay());
                    trunggian.setMaSanBayTG(MaSanBayTG4);
                    trunggian.setThoiGianDung(LocalTime.parse(ThoiGianDung4));
                    String GhiChu4 = request.getParameter("ghiChu4");
                    trunggian.setGhiChu(GhiChu4);   
                    flightService.createTrunggian(trunggian);
                }

                String ngayGioBay = chuyenbay.getNgayGioBay();
                String[] parts = ngayGioBay.split("-");
                String year = parts[0];
                int nam = Integer.parseInt(year);
                String month = parts[1];
                int thang = Integer.parseInt(month);

                bcnam = flightService.getTBCNam(nam);
                if(bcnam == null) {
                    bcnam = new BCDT_Nam();
                    bcnam.setNam(nam);
                    bcnam.setTongDoanhThu(0);
                    flightService.createBCDTNam(bcnam);
                }

                ctbcn = flightService.getBCNam(thang, nam);
                if(ctbcn == null) {
                    ctbcn = new CT_BCDT_Nam();
                    ctbcn.setThang(thang);
                    ctbcn.setNam(nam);
                    ctbcn.setDoanhThu(0);
                    ctbcn.setTiLe(0);
                }
                ctbcn.setSoChuyenBay(ctbcn.getSoChuyenBay()+1);
                flightService.createCTBCDTNam(ctbcn);
                
                ctbct = new CT_BCDT_Thang();
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
            //response = "Flight is not added for flight " + chuyenbay.getMaChuyenBay();
            response = exception.getMessage();
        }
        model.addAttribute("response", response);
        return "announcement";
    }
}
