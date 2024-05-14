package com.example.qlbvmb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.qlbvmb.service.FlightService;
import com.example.qlbvmb.model.ThamSo;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ThayDoiQuiDinh {
    
    @Autowired
    FlightService flightService;

    @GetMapping("/{role}/ThamSo")
    public String thayDoiThamSo(@PathVariable("role") String role, Model model){
        model.addAttribute("role", role);
        ThamSo thamso = flightService.getThamSo();
        model.addAttribute("thamso", thamso);
        return "Thamso";
    }

    @PostMapping("/{role}/ThamSo")
    public String thayDoiThamSo1(Model model, HttpServletRequest request){
        String tgBayToiThieu = request.getParameter("tgBayToiThieu");
        System.out.println(tgBayToiThieu);
        String tgDungToiThieu = request.getParameter("tgDungToiThieu");
        System.out.println(tgDungToiThieu);
        String tgDungToiDa = request.getParameter("tgDungToiDa");
        System.out.println(tgDungToiDa);
        String soSanBayTGToiDa = request.getParameter("soSanBayTGToiDa");
        System.out.println(soSanBayTGToiDa);
        String tgDatVeChamNhat = request.getParameter("tgDatVeChamNhat");
        System.out.println(tgDatVeChamNhat);
        String tgHuyChamNhat = request.getParameter("tgHuyChamNhat");
        System.out.println(tgHuyChamNhat);
        ThamSo thamso = flightService.getThamSo();
        if(tgBayToiThieu != null) thamso.setTgBayToiThieu(tgBayToiThieu);
        if(tgDungToiThieu != null) thamso.setTgDungToiThieu(tgDungToiThieu);
        if(tgDungToiDa != null) thamso.setTgDungToiDa(tgDungToiDa);
        if(soSanBayTGToiDa != null) thamso.setSoSanBayTGToiDa(Integer.parseInt(soSanBayTGToiDa));
        if(tgDatVeChamNhat != null) thamso.setTgDatVeChamNhat(Integer.parseInt(tgDatVeChamNhat));
        if(tgHuyChamNhat != null) thamso.setTgHuyChamNhat(Integer.parseInt(tgHuyChamNhat));
        flightService.createThamSo(thamso);
        model.addAttribute("thamso", thamso);
        return "redirect:/ADMIN/ThamSo";
    }
}
