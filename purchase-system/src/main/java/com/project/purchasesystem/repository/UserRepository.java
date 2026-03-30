package com.project.purchasesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.purchasesystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);
}