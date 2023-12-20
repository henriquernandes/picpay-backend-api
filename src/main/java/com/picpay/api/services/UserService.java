package com.picpay.api.services;

import com.picpay.api.domains.user.User;
import com.picpay.api.domains.user.UserType;
import com.picpay.api.dtos.UserDTO;
import com.picpay.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User user, BigDecimal amount) throws Exception {
        if(user.getType() == UserType.RETAILER) {
            throw new Exception("Retailers cannot make transactions only receive them");
        }

        if(user.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }

    }
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(UserDTO userData) {
        User newUser = new User(userData);
        this.save(newUser);
        return newUser;
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
