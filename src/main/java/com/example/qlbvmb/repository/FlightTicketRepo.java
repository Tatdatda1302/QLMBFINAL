package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.FlightTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightTicketRepo extends CrudRepository<FlightTicket, String>{
    List<FlightTicket> findByMaHK(int maHK);
    FlightTicket findByMaVeAndMaHK(String maVe, int maHK);
}
