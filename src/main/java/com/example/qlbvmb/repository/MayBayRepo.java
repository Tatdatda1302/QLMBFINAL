package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.Maybay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MayBayRepo extends CrudRepository<Maybay, String>{
    List<Maybay> findByMaMB(String maMayBay);
}
