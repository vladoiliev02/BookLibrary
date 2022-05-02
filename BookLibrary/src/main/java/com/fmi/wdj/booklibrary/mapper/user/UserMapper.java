package com.fmi.wdj.booklibrary.mapper.user;

import com.fmi.wdj.booklibrary.dto.user.UserDto;
import com.fmi.wdj.booklibrary.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        return dto;
    }
}
