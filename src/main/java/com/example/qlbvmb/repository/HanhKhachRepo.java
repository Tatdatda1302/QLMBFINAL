package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.HanhKhach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HanhKhachRepo extends CrudRepository<HanhKhach, Integer>{
    HanhKhach findByDinhDanh(String dinhDanh);
    HanhKhach findByMaHK(int maHK);
}
