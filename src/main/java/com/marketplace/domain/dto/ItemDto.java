package com.marketplace.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ItemDto {

    private UUID itemUuid;

    private Double price;

    private List<String> listOfImageUrls;

    private String description;
}
