package com.marketplace.domain.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    @GeneratedValue
    private UUID userUuid;

    @NotEmpty
    private String email;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String password;
}
