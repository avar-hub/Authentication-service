package com.security.securityservice.service;

import com.security.securityservice.entity.CustomUserDetails;
import com.security.securityservice.entity.UserCredential;
import com.security.securityservice.repository.UserCredRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserCredRepo userCredRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential=userCredRepo.findByName(username);
        return userCredential.map(CustomUserDetails::new).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
