package com.nzei.bankapp.services;

import com.nzei.bankapp.entity.User;
import com.nzei.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String name) {
        return userRepository.findByFullName(name);
    }

    public User depositMoney(Long accountNumber, Long amount) {
        User userToDeposit = userRepository.findByAccountNumber(accountNumber);
        Long updatedBalance = userToDeposit.getAccountBalance().longValue() + amount;
        userToDeposit.setAccountBalance(updatedBalance);
        return userRepository.save(userToDeposit);
    }

    public User withdrawMoney(Long accountNumber, Long amount) {
        User userToWithdraw = userRepository.findByAccountNumber(accountNumber);
        Long updatedBalance = userToWithdraw.getAccountBalance() - amount;
        userToWithdraw.setAccountBalance(updatedBalance);
        return userRepository.save(userToWithdraw);
    }

    private String generateSecurityCode(User user) {
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));
        String sc = user.getFullName().substring(0, 2).toUpperCase() + id;

        if (sc.equals(userRepository.findBySecurityCode(sc))) {
            generateSecurityCode(user);
        }
        return sc;
    }
}
