package com.marketplace.service;

import com.marketplace.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean isAlreadyRegistered(UserDto userDto);

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
