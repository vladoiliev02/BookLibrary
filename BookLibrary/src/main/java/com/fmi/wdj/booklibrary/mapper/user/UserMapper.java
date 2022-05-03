package com.fmi.wdj.booklibrary.mapper.user;

import com.fmi.wdj.booklibrary.dto.user.UserInfoDto;
import com.fmi.wdj.booklibrary.dto.user.UserDto;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.model.user.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setDetails(toUserInfoDto(user.getInfo()));

        return dto;
    }

    public UserInfoDto toUserInfoDto(UserInfo userInfo) {
        return new UserInfoDto(
                userInfo.getEmail(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getCountry(),
                userInfo.getCity(),
                userInfo.getStreet()
        );
    }

    public User fromUserDto(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setInfo(fromUserInfoDto(userDto.getDetails()));

        return user;
    }

    public UserInfo fromUserInfoDto(UserInfoDto userInfoDto) {
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(userInfoDto.getEmail());
        userInfo.setFirstName(userInfoDto.getFirstName());
        userInfo.setLastName(userInfoDto.getLastName());
        userInfo.setPhoneNumber(userInfoDto.getPhoneNumber());
        userInfo.setCountry(userInfoDto.getCountry());
        userInfo.setCity(userInfoDto.getCity());
        userInfo.setStreet(userInfoDto.getStreet());

        return userInfo;
    }
}
