package com.example.qlbvmb.service;

import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.repository.FlightRepo;
import com.example.qlbvmb.repository.SanBayRepo;
import com.example.qlbvmb.repository.CTHangVeRepo;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.repository.TrungGianRepo;
import com.example.qlbvmb.model.SanBay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepo flight_repo;

    @Autowired
    SanBayRepo sanbay_repo;

    @Autowired
    CTHangVeRepo cthangve_repo;

    @Autowired
    TrungGianRepo trunggian_repo;

    public Chuyenbay createChuyenbay(Chuyenbay chuyenbay) {
        return flight_repo.save(chuyenbay);
    }

    public CtHangve createCtHangve(CtHangve cthangve) {
        return cthangve_repo.save(cthangve);
    }

    public TrungGian createTrunggian(TrungGian trunggian) {
        return trunggian_repo.save(trunggian);
    }

    public boolean isFlightExist(String maChuyenbay) {
        List<Chuyenbay> chuyenbay = flight_repo.findByMaChuyenBay(maChuyenbay);
        return !chuyenbay.isEmpty();
    }

    public boolean isSanBayExist(String maSanbay) {
        List<SanBay> sanbay = sanbay_repo.findByMaSanBay(maSanbay);
        return !sanbay.isEmpty();
    }
}
