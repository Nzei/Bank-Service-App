package com.nzei.bankapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @Column(unique = true, nullable = false)
    private String securityCode;

    @Column(unique = true, nullable = false)
    private Long accountNumber;

    @Column(nullable = false)
    private Long accountBalance;

    public User() {
        
    }

    public User(Long id, String fullName, Long phoneNumber, String securityCode, Long accountNumber, Long accountBalance) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.securityCode = securityCode;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

}
