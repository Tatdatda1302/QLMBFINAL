package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.HangVe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HangVeRepo extends CrudRepository<HangVe, String>{
    List<HangVe> findByMaHangVe(String maHangVe);
}
