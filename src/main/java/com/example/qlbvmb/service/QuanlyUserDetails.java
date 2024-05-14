package com.example.qlbvmb.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.repository.CustomerRepo;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import com.example.qlbvmb.model.CustomerUserDetails;

@Service
public class QuanlyUserDetails implements UserDetailsService{

    @Autowired
    private UserService userService;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customers = userService.getUser(username);
        if(customers == null) {
            throw new UsernameNotFoundException("User details not found for username " + username);
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(customers.getRole()));

        return new CustomerUserDetails(customers, authorities);
    }


}
