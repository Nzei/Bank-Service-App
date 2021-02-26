package com.nzei.bankapp.controller;

import com.nzei.bankapp.entity.User;
import com.nzei.bankapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String defaultMessage() {
        return "Endpoint works fine";
    }


    @PostMapping(value = "/save", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getall")
    public List<User> getAllUsers(){
      return userService.getAllUsers();
    }

    @GetMapping("get-by-name/{name}")
    public User getByName(@PathVariable String name) {
       return userService.findUserByName(name);
    }

    @GetMapping("/deposit/{accountNumber}/{amount}")
    public User depositMoney(@PathVariable Long accountNumber, @PathVariable Long amount) {
        return userService.depositMoney(accountNumber,amount);
    }

    @GetMapping("/withdraw/{accountNumber}/{amount}")
    public User withdrawMoney(@PathVariable Long accountNumber, @PathVariable Long amount) {
        return userService.withdrawMoney(accountNumber,amount);
    }

}
