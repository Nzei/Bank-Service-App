package com.nzei.bankapp.services;

import com.nzei.bankapp.entity.User;
import com.nzei.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        user.setSecurityCode(generateSecurityCode(user));
        user.setAccountBalance(5000L);
        return userRepository.save(user);
    }

    private String generateSecurityCode(User user) {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        String sc = user.getFullName().substring(0,2).toUpperCase() + id;

        if(sc.equals(userRepository.findBySecurityCode(sc))){
            generateSecurityCode(user);
        }
        return sc;
    }
}
