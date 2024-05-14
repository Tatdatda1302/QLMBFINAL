package com.example.qlbvmb.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.qlbvmb.model.BCDT_Nam;
import org.springframework.stereotype.Repository;

@Repository
public interface TDTNamRepo extends CrudRepository<BCDT_Nam, Integer>{
    BCDT_Nam findByNam (int nam);
}
