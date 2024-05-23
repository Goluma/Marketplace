package com.marketplace.service.impl;

import com.marketplace.domain.dto.UserDto;
import com.marketplace.domain.entity.UserEntity;
import com.marketplace.mapper.UserMapper;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    public final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isAlreadyRegistered(UserDto userDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmailLike(userDto.getEmail());
        return userEntity.isPresent();
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(new HashSet<>(List.of(/*"ROLE_ADMIN",*/ "ROLE_USER")));
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.userEntityToUserDto(savedUserEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userEntityToUserDto).collect(Collectors.toList());
    }
}
