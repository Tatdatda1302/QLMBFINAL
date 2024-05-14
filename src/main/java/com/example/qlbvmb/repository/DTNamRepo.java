package com.example.qlbvmb.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.qlbvmb.TableID.CTBCDTNID;
import com.example.qlbvmb.model.CT_BCDT_Nam;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DTNamRepo extends CrudRepository<CT_BCDT_Nam, CTBCDTNID>{
    CT_BCDT_Nam findByThangAndNam (int thang, int nam);
    List<CT_BCDT_Nam> findByNam (int nam);
}
