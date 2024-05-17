package com.marketplace.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    private String pathToImage;

    @NotEmpty
    private String description;

    private UUID userUuid;

    private MultipartFile image;

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemUuid=" + itemUuid +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", pathToImage='" + pathToImage + '\'' +
                ", description='" + description + '\'' +
                ", userUuid=" + userUuid +
                ", image=" + image +
                '}';
    }
}
