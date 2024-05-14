package com.example.qlbvmb.controller;
import com.example.qlbvmb.model.HangMBSX;
import com.example.qlbvmb.model.HangVe;
import com.example.qlbvmb.model.LoaiHK;
import com.example.qlbvmb.model.Maybay;
import com.example.qlbvmb.model.SanBay;
import com.example.qlbvmb.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class ThayDoiSoLuong {

    @Autowired
    private FlightService flightService;
    
    @GetMapping("/{role}/Airport")
    public String showAirport(@PathVariable("role") String role ,Model model) {
        Iterable<SanBay> sanBay = flightService.getAllSanbay();
        model.addAttribute("sanBay", sanBay);
        model.addAttribute("role", role);
        return "Sanbay";
    }

    @GetMapping("/{role}/deleteAirport/{maSanBay}")
    public String deleteAirport(@PathVariable("role") String role, @PathVariable("maSanBay") String maSanBay, Model model) {
        flightService.deleteSanBay(maSanBay);
        return "redirect:/{role}/Airport";
    }

    @GetMapping("/{role}/addAirport")
    public String addAirport(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        return "Themsb";
    }

    @PostMapping("/{role}/addAirport")
    public String addAirport(@PathVariable("role") String role, @ModelAttribute SanBay sanBay, Model model, HttpServletRequest request) {
        String response;
        String maSanBay = request.getParameter("maSanBay");
        if(flightService.isSanBayExist(maSanBay)) {
            response = "Mã sân bay đã tồn tại";
            model.addAttribute("response", response);
            return "Themsb";
        }
        flightService.createSanbay(sanBay);
        response = "Thêm sân bay thành công";
        model.addAttribute("response", response);
        return "redirect:/{role}/Airport";
    }


    @GetMapping("/{role}/HangMB")
    public String showHangMB(@PathVariable("role") String role, Model model) {
        Iterable<HangMBSX> hangMBSX = flightService.getAllHangMBSX();
        model.addAttribute("hangMBSX", hangMBSX);
        model.addAttribute("role", role);
        return "HangMB";
    }

    @GetMapping("/{role}/deleteHangMB/{maHMB}")
    public String deleteHangMB(@PathVariable("role") String role, @PathVariable("maHMB") String maHMB, Model model) {
        flightService.deleteHangMBSX(maHMB);
        return "redirect:/{role}/HangMB";
    }

    @GetMapping("/{role}/addHangMBSX")
    public String addHangMBSX(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        return "ThemHang";
    }

    @PostMapping("/{role}/addHangMBSX")
    public String addHangMBSX(@PathVariable("role") String role, Model model, HangMBSX hangMBSX, HttpServletRequest request) {
        String response;
        String maHMB = request.getParameter("maHMB");
        if(flightService.isHangMBSXExist(maHMB)) {
            response = "Mã hãng máy bay đã tồn tại";
            model.addAttribute("response", response);
            return "ThemHang";
        }
        flightService.createHangMBSX(hangMBSX);
        response = "Thêm hãng máy bay thành công";
        model.addAttribute("response", response);
        return "redirect:/{role}/HangMB";
    }

    @GetMapping("/{role}/Maybay")
    public String showMayBay(@PathVariable("role") String role, Model model) {
        Iterable<Maybay> maybay = flightService.getAllMaybay();
        model.addAttribute("maybay", maybay);
        model.addAttribute("role", role);
        return "Maybay";
    }

    @GetMapping("/{role}/deleteMaybay/{maMB}")
    public String deleteMaybay(@PathVariable("role") String role, @PathVariable("maMB") String maMB, Model model) {
        flightService.deleteMaybay(maMB);
        return "redirect:/{role}/Maybay";
    }

    @GetMapping("/{role}/addMaybay")
    public String addMaybay(@PathVariable("role") String role, Model model) {
        Iterable<HangMBSX> hangMBSX = flightService.getAllHangMBSX();
        model.addAttribute("hangMBSX", hangMBSX);
        model.addAttribute("role", role);
        return "Themmb";
    }

    @PostMapping("/{role}/addMaybay")
    public String addMaybay(@PathVariable("role") String role, Model model, Maybay maybay, HttpServletRequest request) {
        String response;
        String maMB = request.getParameter("maMB");
        if(flightService.isMaybayExist(maMB)) {
            response = "Mã máy bay đã tồn tại";
            model.addAttribute("response", response);
            return "Themmb";
        }
        flightService.createMaybay(maybay);
        response = "Thêm máy bay thành công";
        model.addAttribute("response", response);
        return "redirect:/{role}/Maybay";
    }


    @GetMapping("/{role}/HangVe")
    public String showHangVe(@PathVariable("role") String role, Model model) {
        Iterable<HangVe> hangVe = flightService.getAllHangVe();
        model.addAttribute("hangVe", hangVe);
        model.addAttribute("role", role);
        return "HangVe";
    }

    @GetMapping("/{role}/deleteHangVe/{maHangVe}")
    public String deleteHangVe(@PathVariable("role") String role, @PathVariable("maHangVe") String maHangVe, Model model) {
        flightService.deleteHangVe(maHangVe);
        return "redirect:/{role}/HangVe";
    }

    @GetMapping("/{role}/addHangVe")
    public String addHangVe(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        return "ThemHangVe";
    }

    @PostMapping("/{role}/addHangVe")
    public String addHangVe(@PathVariable("role") String role, Model model, HangVe hangVe, HttpServletRequest request) {
        String response;
        String maHangVe = request.getParameter("maHangVe");
        if(flightService.isHangVeExist(maHangVe)) {
            response = "Mã hạng vé đã tồn tại";
            model.addAttribute("response", response);
            return "ThemHangVe";
        }
        flightService.createHangVe(hangVe);
        response = "Thêm hạng vé thành công";
        model.addAttribute("response", response);
        return "redirect:/{role}/HangVe";
    }


    @GetMapping("/{role}/LoaiHK")
    public String showLoaiHK(@PathVariable("role") String role, Model model) {
        Iterable<LoaiHK> loaiHK = flightService.getAllLoaiHK();
        model.addAttribute("loaiHK", loaiHK);
        model.addAttribute("role", role);
        return "LoaiHK";
    }

    @GetMapping("/{role}/deleteLoaiHK/{maLHK}")
    public String deleteLoaiHK(@PathVariable("role") String role, @PathVariable("maLHK") String maLHK, Model model) {
        flightService.deleteLoaiHK(maLHK);
        return "redirect:/{role}/LoaiHK";
    }

    @GetMapping("/{role}/addLoaiHK")
    public String addLoaiHK(@PathVariable("role") String role, Model model) {
        model.addAttribute("role", role);
        return "ThemLoaiHK";
    }

    @PostMapping("/{role}/addLoaiHK")
    public String addLoaiHK(@PathVariable("role") String role, Model model, LoaiHK loaiHK, HttpServletRequest request) {
        String response;
        String maLHK = request.getParameter("maLHK");
        if(flightService.isLoaiHKExist(maLHK)) {
            response = "Mã loại hành khách đã tồn tại";
            model.addAttribute("response", response);
            return "ThemLoaiHK";
        }
        flightService.createLoaiHK(loaiHK);
        response = "Thêm loại hành khách thành công";
        model.addAttribute("response", response);
        return "redirect:/{role}/LoaiHK";
    }
}
