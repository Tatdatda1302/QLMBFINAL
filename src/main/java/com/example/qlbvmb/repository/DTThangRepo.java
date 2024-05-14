package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.CT_BCDT_Thang;
import com.example.qlbvmb.TableID.CTBCDTTID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DTThangRepo extends CrudRepository<CT_BCDT_Thang, CTBCDTTID>{
    CT_BCDT_Thang findByMaChuyenBay (String maChuyenBay);
    List<CT_BCDT_Thang> findByThangAndNam (int thang, int nam);
}
