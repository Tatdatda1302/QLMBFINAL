package com.example.qlbvmb.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.model.BCDT_Nam;
import com.example.qlbvmb.service.FlightService;

@Controller
public class DoanhThu {

    @Autowired
    FlightService flightService;
    

    @GetMapping("/{role}/BCDT_Thang")
    public String showBCDT_Thang(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        return "index_doanhthu_thang";
    }

    @PostMapping("/{role}/BCDT_Thang")
    public String BCDT_ThangPage(@PathVariable("role") String role, Model model, HttpServletRequest request) {
        String thang = request.getParameter("month");
        String nam = request.getParameter("year");
        int month = Integer.parseInt(thang);
        int year = Integer.parseInt(nam);
        BCDT_Nam bcdt_nam = flightService.getTBCNam(year);
        CT_BCDT_Nam ct_bcdt_nam = flightService.getBCNam(month, year);
        Iterable<CT_BCDT_Thang> ct_bcdt_thang = flightService.getBCThangByThangNam(month, year);

        DecimalFormat df = new DecimalFormat("#.##");
        double result = (ct_bcdt_nam.getDoanhThu() / bcdt_nam.getTongDoanhThu())*100;
        String tiLe = df.format(result);
        ct_bcdt_nam.setTiLe(Double.parseDouble(tiLe));
        flightService.createCTBCDTNam(ct_bcdt_nam);

        for (CT_BCDT_Thang ct : ct_bcdt_thang) {
            result = (ct.getDoanhThu() / ct_bcdt_nam.getDoanhThu())*100;
            tiLe = df.format(result);
            ct.setTiLe(Double.parseDouble(tiLe));
            flightService.createCTBCDTThang(ct);
        }

        Iterable<CT_BCDT_Nam> tct_bcdt_nam = flightService.getBCNamByNam(year);

        model.addAttribute("ct_bcdt_thang", ct_bcdt_thang);
        model.addAttribute("ct_bcdt_nam", ct_bcdt_nam);
        model.addAttribute("bcdt_nam", bcdt_nam);
        model.addAttribute("tct_bcdt_nam", tct_bcdt_nam);
        return "index_doanhthu_thang";
    }
}
