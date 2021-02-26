package com.nzei.bankapp.repository;

import com.nzei.bankapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByFullName(String fullName);

    public User findBySecurityCode(String securityCode);

    public User findByAccountNumber(Long accountNumber);

}
