package com.marketplace.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ItemDto {

    private UUID itemUuid;

    @NotEmpty
    private String name;

    @NotEmpty
    private String price;

    @NotEmpty
    private byte[] image;

    @NotEmpty
    private String description;

    private UUID userUuid;

}
