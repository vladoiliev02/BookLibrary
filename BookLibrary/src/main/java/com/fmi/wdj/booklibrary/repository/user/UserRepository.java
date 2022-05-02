package com.fmi.wdj.booklibrary.repository.user;

import com.fmi.wdj.booklibrary.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
