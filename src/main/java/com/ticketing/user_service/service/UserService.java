package com.ticketing.user_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ticketing.user_service.entity.Role;
import com.ticketing.user_service.entity.User;
import com.ticketing.user_service.repository.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String email, String password, String name, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setHashedPassword(password);
        user.setName(name);
        user.setRoles(role);
        return userRepository.save(user);
    }

    public Optional <User> findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return Optional.ofNullable(user.orElse(null));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public Object getUserById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }
}
