package com.example.demo.controller;

import com.example.demo.entity.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AuthenAPI {
    @GetMapping("/api/accounts")
    public List<Account> getAccounts(){
        System.out.println("getAccounts");
        return null;
    }
    @PostMapping("/api/account")
    public Account createAccount(@RequestBody Account account){
        System.out.println("createAccount");
        return account;
    }
    @PostMapping("/api/accounts")
    public Account createAccounts(@RequestBody Account account){
        System.out.println("createAccount");
        return account;
    }
}
