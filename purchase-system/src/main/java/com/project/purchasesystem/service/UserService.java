package com.project.purchasesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.purchasesystem.entity.User;
import com.project.purchasesystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // READ
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // DELETE
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}