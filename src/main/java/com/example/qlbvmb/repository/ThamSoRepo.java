package com.example.qlbvmb.repository;

import com.example.qlbvmb.model.ThamSo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThamSoRepo extends CrudRepository<ThamSo, Integer>{
    List<ThamSo> findAll();
}
