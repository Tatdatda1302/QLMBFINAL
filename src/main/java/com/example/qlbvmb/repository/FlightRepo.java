package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.Chuyenbay;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends CrudRepository<Chuyenbay, String>{
    List<Chuyenbay> findByMaChuyenBay(String maChuyenBay);
}
