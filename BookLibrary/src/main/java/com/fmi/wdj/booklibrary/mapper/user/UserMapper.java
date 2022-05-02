package com.fmi.wdj.booklibrary.mapper.user;

import com.fmi.wdj.booklibrary.dto.user.UserDetailsDto;
import com.fmi.wdj.booklibrary.dto.user.UserDto;
import com.fmi.wdj.booklibrary.model.user.User;
import com.fmi.wdj.booklibrary.model.user.UserDetails;
import com.fmi.wdj.booklibrary.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setDetails(toUserDetailsDto(user.getDetails()));

        return dto;
    }

    public UserDetailsDto toUserDetailsDto(UserDetails userDetails) {
        return new UserDetailsDto(
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getPhoneNumber(),
                userDetails.getCountry(),
                userDetails.getCity(),
                userDetails.getStreet()
        );
    }

    public User fromUserDto(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setDetails(fromUserDetailsDto(userDto.getDetails()));

        return user;
    }

    public UserDetails fromUserDetailsDto(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();

        userDetails.setEmail(userDetailsDto.getEmail());
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setPhoneNumber(userDetailsDto.getPhoneNumber());
        userDetails.setCountry(userDetailsDto.getCountry());
        userDetails.setCity(userDetailsDto.getCity());
        userDetails.setStreet(userDetailsDto.getStreet());

        return userDetails;
    }
}
