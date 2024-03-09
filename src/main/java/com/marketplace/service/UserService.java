package com.marketplace.service;

import com.marketplace.domain.dto.UserDto;

public interface UserService {
    boolean isAlreadyRegistered(UserDto userDto);

    UserDto registerUser(UserDto userDto);
}
