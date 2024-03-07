package com.marketplace.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID userUuid;

    private String email;

    private String nickName;

    private String password;
}
