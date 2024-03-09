package com.marketplace.service.impl;

import com.marketplace.domain.dto.UserDto;
import com.marketplace.domain.entity.UserEntity;
import com.marketplace.mapper.UserMapper;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Log
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    public final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean isAlreadyRegistered(UserDto userDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmailLike(userDto.getEmail());
        return userEntity.isPresent();
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapToEntity(userDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        log.info("Saved new user");
        return userMapper.mapToDto(savedUserEntity);
    }
}
