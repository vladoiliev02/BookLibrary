package com.fmi.wdj.booklibrary.controller.user;

import com.fmi.wdj.booklibrary.dto.user.UserDetailsDto;
import com.fmi.wdj.booklibrary.dto.user.UserDto;
import com.fmi.wdj.booklibrary.mapper.user.UserMapper;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.model.user.UserDetails;
import com.fmi.wdj.booklibrary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/api/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        User result = userService.getUserByUsername(username);
        return userMapper.toUserDto(result);
    }

    @Override
    @GetMapping("/details/{username}")
    public UserDetailsDto getUserDetails(@PathVariable String username) {
        UserDetails userDetails =  userService.getDetailsForUser(username);
        return userMapper.toUserDetailsDto(userDetails);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody @Valid UserDto user) {
        User newUser = userService.saveUser(userMapper.fromUserDto(user));
        return userMapper.toUserDto(newUser);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto user) {
        boolean isUpdate = userService.exists(user.getUsername());

        User updatedUser = userMapper.fromUserDto(user);
        userService.saveUser(updatedUser);

        UserDto result = userMapper.toUserDto(updatedUser);
        return isUpdate ? new ResponseEntity<>(result, HttpStatus.OK)
                        : new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping({"/{username}"})
    public void deleteUser(@PathVariable String username) {
        userService.removeUser(username);
    }
}
