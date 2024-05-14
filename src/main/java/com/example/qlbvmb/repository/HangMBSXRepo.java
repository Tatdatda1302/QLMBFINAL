package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.HangMBSX;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HangMBSXRepo extends CrudRepository<HangMBSX, String>{
    List<HangMBSX> findByMaHMB(String maHangMB);
}
