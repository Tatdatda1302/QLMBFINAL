package com.example.qlbvmb.repository;
import com.example.qlbvmb.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer>{
    Customer findByUserName(String userName);
    Customer findByID(int ID);
}
