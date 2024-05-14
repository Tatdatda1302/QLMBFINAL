package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.Chuyenbay;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.time.LocalDate;

@Repository
public interface FlightRepo extends CrudRepository<Chuyenbay, String>{
    List<Chuyenbay> findByMaChuyenBay(String maChuyenBay);

    @Query("SELECT c FROM Chuyenbay c WHERE DATE(c.ngayGioBay) = :ngayKhoiHanh")
    List<Chuyenbay> findByNgayGioBay(LocalDate ngayKhoiHanh);
    
    @Query("SELECT c FROM Chuyenbay c WHERE c.maSanBayDi = :maSanBayDi AND c.maSanBayDen = :maSanBayDen AND DATE(c.ngayGioBay) = :ngayKhoiHanh")
    Iterable<Chuyenbay> findByMaSanBayDiAndMaSanBayDenAndNgayGioBay(String maSanBayDi, String maSanBayDen, LocalDate ngayKhoiHanh);
}
