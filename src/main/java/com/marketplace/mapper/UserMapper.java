package com.marketplace.mapper;

import com.marketplace.domain.dto.UserDto;
import com.marketplace.domain.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public UserDto mapToDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity mapToEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }
}
