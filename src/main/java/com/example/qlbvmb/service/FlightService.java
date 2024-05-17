package com.example.qlbvmb.service;

import com.example.qlbvmb.model.BCDT_Nam;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import com.example.qlbvmb.model.Chuyenbay;
import com.example.qlbvmb.model.CtHangve;
import com.example.qlbvmb.repository.FlightRepo;
import com.example.qlbvmb.repository.SanBayRepo;
import com.example.qlbvmb.repository.CTHangVeRepo;
import com.example.qlbvmb.repository.DTNamRepo;
import com.example.qlbvmb.repository.DTThangRepo;
import com.example.qlbvmb.model.TrungGian;
import com.example.qlbvmb.repository.TrungGianRepo;
import com.example.qlbvmb.repository.SoGheRepo;
import com.example.qlbvmb.repository.TDTNamRepo;
import com.example.qlbvmb.repository.MayBayRepo;
import com.example.qlbvmb.model.SanBay;
import com.example.qlbvmb.model.DSGhe;
import com.example.qlbvmb.model.Maybay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import com.example.qlbvmb.model.PhieuDatCho;
import com.example.qlbvmb.repository.PhieuDatChoRepo;
import com.example.qlbvmb.model.FlightTicket;
import com.example.qlbvmb.repository.FlightTicketRepo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.repository.ThamSoRepo;
import com.example.qlbvmb.model.ThamSo;
import com.example.qlbvmb.model.HangMBSX;
import com.example.qlbvmb.repository.HangMBSXRepo;
import com.example.qlbvmb.model.HangVe;
import com.example.qlbvmb.repository.HangVeRepo;
import com.example.qlbvmb.model.LoaiHK;
import com.example.qlbvmb.repository.LoaiHKRepo;


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

    @Autowired
    SoGheRepo soghe_repo;

    @Autowired
    MayBayRepo maybay_repo;

    @Autowired
    PhieuDatChoRepo phieuDatCho_repo;

    @Autowired
    FlightTicketRepo flightTicket_repo;

    @Autowired
    DTThangRepo dtThangRepo;

    @Autowired
    DTNamRepo dtNamRepo;

    @Autowired
    TDTNamRepo bcNamRepo;

    @Autowired
    ThamSoRepo thamSoRepo;

    @Autowired
    HangMBSXRepo hangMBSX_repo;

    @Autowired
    HangVeRepo hangve_repo;

    @Autowired
    LoaiHKRepo loaiHK_repo;

    public Chuyenbay createChuyenbay(Chuyenbay chuyenbay) {
        return flight_repo.save(chuyenbay);
    }

    public CtHangve createCtHangve(CtHangve cthangve) {
        return cthangve_repo.save(cthangve);
    }

    public TrungGian createTrunggian(TrungGian trunggian) {
        return trunggian_repo.save(trunggian);
    }

    public DSGhe createDSGhe(DSGhe dsghe) {
        return soghe_repo.save(dsghe);
    }

    public PhieuDatCho createPhieuDatCho(PhieuDatCho phieuDatCho) {
        return phieuDatCho_repo.save(phieuDatCho);
    }

    public FlightTicket createFlightTicket(FlightTicket flightTicket) {
        return flightTicket_repo.save(flightTicket);
    }
    
    public CT_BCDT_Thang createCTBCDTThang(CT_BCDT_Thang ctbcDtThang) {
        return dtThangRepo.save(ctbcDtThang);
    }

    public CT_BCDT_Nam createCTBCDTNam(CT_BCDT_Nam ctbcDtNam) {
        return dtNamRepo.save(ctbcDtNam);
    }

    public BCDT_Nam createBCDTNam(BCDT_Nam bcDtNam) {
        return bcNamRepo.save(bcDtNam);
    }

    public ThamSo createThamSo(ThamSo thamso) {
        return thamSoRepo.save(thamso);
    }

    public SanBay createSanbay(SanBay sanbay) {
        return sanbay_repo.save(sanbay);
    }
    public void deleteSanBay(String maSanBay) {
        sanbay_repo.deleteById(maSanBay);
    }

    public Maybay createMaybay(Maybay maybay) {
        return maybay_repo.save(maybay);
    }

    public boolean isMaybayExist(String maMaybay) {
        List<Maybay> maybay = maybay_repo.findByMaMB(maMaybay);
        return !maybay.isEmpty();
    }

    public void deleteMaybay(String maMaybay) {
        maybay_repo.deleteById(maMaybay);
    }

    public boolean isHangMBSXExist(String maHangMBSX) {
        List<HangMBSX> hangMBSX = hangMBSX_repo.findByMaHMB(maHangMBSX);
        return !hangMBSX.isEmpty();
    }

    public void deleteHangMBSX(String maHangMBSX) {
        hangMBSX_repo.deleteById(maHangMBSX);
    }

    public HangMBSX createHangMBSX(HangMBSX hangMBSX) {
        return hangMBSX_repo.save(hangMBSX);
    }

    public boolean isHangVeExist(String maHangVe) {
        List<HangVe> hangve = hangve_repo.findByMaHangVe(maHangVe);
        return !hangve.isEmpty();
    }

    public void deleteHangVe(String maHangVe) {
        hangve_repo.deleteById(maHangVe);
    }

    public HangVe createHangVe(HangVe hangVe) {
        return hangve_repo.save(hangVe);
    }

    public boolean isLoaiHKExist(String maLoaiHK) {
        List<LoaiHK> loaiHK = loaiHK_repo.findByMaLHK(maLoaiHK);
        return !loaiHK.isEmpty();
    }

    public void deleteLoaiHK(String maLoaiHK) {
        loaiHK_repo.deleteById(maLoaiHK);
    }

    public LoaiHK createLoaiHK(LoaiHK loaiHK) {
        return loaiHK_repo.save(loaiHK);
    }

    public void deleteTicket(String maVe) {
        flightTicket_repo.deleteById(maVe);
    }

    public boolean isFlightExist(String maChuyenbay) {
        List<Chuyenbay> chuyenbay = flight_repo.findByMaChuyenBay(maChuyenbay);
        return !chuyenbay.isEmpty();
    }

    public Chuyenbay getFlightById(String maChuyenbay) {
        List<Chuyenbay> chuyenbay = flight_repo.findByMaChuyenBay(maChuyenbay);
        return chuyenbay.get(0);
    }

    public FlightTicket getFlightTicketById(String maVe, int maHK) {
        return flightTicket_repo.findByMaVeAndMaHK(maVe, maHK);
    }

    public PhieuDatCho getPhieuDatCho(String soPhieuDatCho, int maHK) {
        return phieuDatCho_repo.findBySoPhieuDatChoAndMaHK(soPhieuDatCho, maHK);
    }

    public Iterable<PhieuDatCho> getPhieuDatChoById(int maHK) {
        return phieuDatCho_repo.findByMaHK(maHK);
    }

    public CtHangve getVe(String maHangVe, String maChuyenBay) {
        List<CtHangve> cthangve = cthangve_repo.findByMaHangveAndMaChuyenBay(maHangVe, maChuyenBay);
        return cthangve.get(0);
    }

    public Iterable<Chuyenbay> getAllChuyenbay() {
        return flight_repo.findAll();
    }

    public Iterable<Maybay> getAllMaybay() {
        return maybay_repo.findAll();
    }

    public Iterable<SanBay> getAllSanbay() {
        return sanbay_repo.findAll();
    }

    public Iterable<HangMBSX> getAllHangMBSX() {
        return hangMBSX_repo.findAll();
    }

    public Iterable<HangVe> getAllHangVe() {
        return hangve_repo.findAll();
    }

    public Iterable<LoaiHK> getAllLoaiHK() {
        return loaiHK_repo.findAll();
    }

    public Iterable<DSGhe> getGhe(String maMayBay, String maHangVe, String maChuyenBay) {
        return soghe_repo.findByMaMBAndMaHangVeAndGhiChu(maMayBay, maHangVe, maChuyenBay);
    }

    public boolean isSanBayExist(String maSanbay) {
        List<SanBay> sanbay = sanbay_repo.findByMaSanBay(maSanbay);
        return !sanbay.isEmpty();
    }

    public SanBay getSanBayById(String maSanbay) {
        List<SanBay> sanbay = sanbay_repo.findByMaSanBay(maSanbay);
        return sanbay.get(0);
    }

    public Iterable<Chuyenbay> getFlight(String maSanBayDi, String maSanBayDen, LocalDate ngayKhoiHanh) {
        return flight_repo.findByMaSanBayDiAndMaSanBayDenAndNgayGioBay(maSanBayDi, maSanBayDen, ngayKhoiHanh);
    }

    public Iterable<CtHangve> getCtHangVe(String maHangVe, String maChuyenBay) {
        return cthangve_repo.findByMaHangveAndMaChuyenBay(maHangVe, maChuyenBay);
    }

    public Iterable<FlightTicket> getFlightTicket(int maHK) {
        return flightTicket_repo.findByMaHK(maHK);
    }

    public TrungGian getTrungGianByMaChuyenBay(String maChuyenBay) {
        return trunggian_repo.findByMaChuyenBay(maChuyenBay);
    }

    @Transactional
    public void deleteGhe(int soGhe, String maMB, String maHangVe, String maChuyenBay) {
        soghe_repo.deleteBySoGheAndMaMBAndMaHangVeAndGhiChu(soGhe, maMB, maHangVe, maChuyenBay);
    }

    @Transactional
    public void updateByNgayDatVe(String maChuyenBay) {
        phieuDatCho_repo.updateByNgayDatVe(maChuyenBay);
    }

    public Iterable<PhieuDatCho> getPhieuDatChoByMaChuyenBay(String maChuyenBay) {
        return phieuDatCho_repo.findByMaChuyenBay(maChuyenBay);
    }

    public Iterable<Chuyenbay> getFlightByNgayGioBay(LocalDate ngayKhoiHanh) {
        return flight_repo.findByNgayGioBay(ngayKhoiHanh);
    }

    public CT_BCDT_Thang getBCThang(String maChuyenBay) {
        return dtThangRepo.findByMaChuyenBay(maChuyenBay);
    }

    public Iterable<CT_BCDT_Thang> getBCThangByThangNam(int thang, int nam) {
        return dtThangRepo.findByThangAndNam(thang, nam);
    }

    public boolean isBCThangExist(int thang, int nam) {
        List<CT_BCDT_Thang> ctbcDtThang = dtThangRepo.findByThangAndNam(thang, nam);
        return !ctbcDtThang.isEmpty();
    }

    public CT_BCDT_Nam getBCNam(int thang, int nam) {
        return dtNamRepo.findByThangAndNam(thang, nam);
    }

    public Iterable<CT_BCDT_Nam> getBCNamByNam(int nam) {
        return dtNamRepo.findByNam(nam);
    }

    public BCDT_Nam getTBCNam(int nam) {
        return bcNamRepo.findByNam(nam);
    }

    public ThamSo getThamSo() {
        List<ThamSo> thamso = thamSoRepo.findAll();
        return thamso.get(0);
    }


}
