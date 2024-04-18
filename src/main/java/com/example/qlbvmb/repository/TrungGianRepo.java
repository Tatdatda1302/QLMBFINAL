package com.example.qlbvmb.repository;
import com.example.qlbvmb.TableID.TrungGianID;
import com.example.qlbvmb.model.TrungGian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface TrungGianRepo extends CrudRepository<TrungGian, TrungGianID>{
    
}
