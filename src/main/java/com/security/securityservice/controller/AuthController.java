package com.security.securityservice.controller;

import com.security.securityservice.entity.AuthRequest;
import com.security.securityservice.entity.UserCredential;
import com.security.securityservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @GetMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return authService.saveUser(user);
    }

    @GetMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest auth) {
       Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(),auth.getPassword()));
       if(authentication.isAuthenticated())
            return authService.generateToken(auth.getUserName());
       else
           throw new RuntimeException("Not Valid");
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
