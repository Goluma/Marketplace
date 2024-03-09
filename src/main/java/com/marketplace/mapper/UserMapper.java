package com.marketplace.mapper;

import com.marketplace.domain.dto.UserDto;
import com.marketplace.domain.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

        public ModelMapper modelMapper;

        public UserMapper(ModelMapper modelMapper){
                this.modelMapper = modelMapper;
        }

        public UserEntity userDtoToUserEntity(UserDto userDto){
                return modelMapper.map(userDto, UserEntity.class);
        }

        public UserDto userEntityToUserDto(UserEntity userEntity){
                return modelMapper.map(userEntity, UserDto.class);
        }
}
