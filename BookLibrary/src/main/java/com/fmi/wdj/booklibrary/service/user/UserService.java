package com.fmi.wdj.booklibrary.service.user;

import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.model.user.UserDetails;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    void removeUser(String username);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    boolean exists(String username);

    UserDetails getDetailsForUser(String username);
}
