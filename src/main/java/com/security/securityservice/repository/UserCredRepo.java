package com.security.securityservice.repository;

import com.security.securityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredRepo extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential>  findByName(String name);
}
