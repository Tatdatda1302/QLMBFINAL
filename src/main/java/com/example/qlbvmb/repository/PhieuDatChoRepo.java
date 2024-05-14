package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.PhieuDatCho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhieuDatChoRepo extends CrudRepository<PhieuDatCho, String>{
    List<PhieuDatCho> findByMaHK(int maHK);
    PhieuDatCho findBySoPhieuDatChoAndMaHK(String soPhieuDatCho, int maHK);
    List<PhieuDatCho> findByNgayDat(LocalDate ngayDat);
    List<PhieuDatCho> findByMaChuyenBay(String maChuyenBay);

    @Modifying
    @Query("UPDATE PhieuDatCho p SET p.tinhTrang = 'Bị hủy' WHERE p.maChuyenBay = :maChuyenBay")
    void updateByNgayDatVe(String maChuyenBay);
}
