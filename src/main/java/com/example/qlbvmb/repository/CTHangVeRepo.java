package com.example.qlbvmb.repository;
import com.example.qlbvmb.TableID.CTHVID;
import com.example.qlbvmb.model.CtHangve;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CTHangVeRepo extends CrudRepository<CtHangve, CTHVID>{
}
