package com.fmi.wdj.booklibrary.service.user;

import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.model.user.UserInfo;
import com.fmi.wdj.booklibrary.repository.user.UserRepository;
import com.fmi.wdj.booklibrary.security.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.existsById(user.getUsername())) {
            throw new IllegalArgumentException(String.format("User: %s already exists.", user.getUsername()));
        }

        return userRepository.save(user);
    }

    @Override
    public void removeUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public boolean exists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return userRepository.getById(username).getInfo();
    }

    @Override
    public boolean isAdmin(String username) {
        return userRepository.findById(username)
            .map(user -> user.getRole().equals(Role.ADMIN))
            .orElse(false);
    }
}
