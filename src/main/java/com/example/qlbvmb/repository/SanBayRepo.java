package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.SanBay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SanBayRepo extends CrudRepository<SanBay, String>{
    List<SanBay> findByMaSanBay(String maSanBay);
}
