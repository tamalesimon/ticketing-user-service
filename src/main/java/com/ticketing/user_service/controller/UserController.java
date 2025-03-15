package com.ticketing.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.user_service.service.UserService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> getAllUsers() {
        Map<String, String> response = Map.of("users", this.userService.getAllUsers().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email")
    public ResponseEntity<Map <String, String>> getUserByEmail(@RequestParam String email) {
        Map<String, String> response = Map.of("user", this.userService.getUserByEmail(email).toString());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/create")
    public String createUser(@RequestParam String email, @RequestParam String password) {
        this.userService.createUser(email, password);
        return "User created";
    }

}
