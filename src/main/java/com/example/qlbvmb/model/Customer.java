package com.example.qlbvmb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ID;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;
    
    @Column(name = "email")
    private String Email;
    
    @Column(name = "dinhDanh")
    private String dinhDanh;

    @Column(name = "role")
    private String Role;
}
