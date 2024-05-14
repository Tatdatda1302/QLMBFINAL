package com.example.qlbvmb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.qlbvmb.repository.CustomerRepo;
import com.example.qlbvmb.repository.HanhKhachRepo;
import com.example.qlbvmb.model.HanhKhach;
import com.example.qlbvmb.model.Customer;

@Service
public class UserService {
    
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    CustomerRepo customerrepo;

    @Autowired
    HanhKhachRepo hanhKhachRepo;

    public Customer getUser(String username){
        return customerrepo.findByUserName(username);
    }

    public Customer createNewUser(Customer customer){
        customer.setPassWord(passwordEncoder.encode(customer.getPassWord()));
        return customerrepo.save(customer);
    }

    public Customer createUser(Customer customer){
        return customerrepo.save(customer);
    }

    public Iterable<Customer> getAllUser(){
        return customerrepo.findAll();
    }

    public Iterable<HanhKhach> getAllHanhKhach(){
        return hanhKhachRepo.findAll();
    }

    public Customer getUserById(int id){
        return customerrepo.findByID(id);
    }

    public void deleteUser(int id){
        customerrepo.deleteById(id);
    }

    public boolean isUserExist(String username){
        Customer customer = customerrepo.findByUserName(username);
        return customer != null;
    }

    public HanhKhach getHanhKhach(String dinhDanh){
        return hanhKhachRepo.findByDinhDanh(dinhDanh);
    }
    
    public HanhKhach getHanhKhachById(int maHK){
        return hanhKhachRepo.findByMaHK(maHK);
    }
    
    public boolean isHanhKhachExist(String dinhDanh){
        HanhKhach hanhKhach = hanhKhachRepo.findByDinhDanh(dinhDanh);
        return hanhKhach != null;
    }

    public HanhKhach createNewHanhKhach(HanhKhach hanhKhach){
        return hanhKhachRepo.save(hanhKhach);
    }
}
