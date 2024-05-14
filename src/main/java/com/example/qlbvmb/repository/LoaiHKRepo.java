package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.LoaiHK;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiHKRepo extends CrudRepository<LoaiHK, String>{
    List<LoaiHK> findByMaLHK(String maLHK);
}
