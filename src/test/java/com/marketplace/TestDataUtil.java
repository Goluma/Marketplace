package com.marketplace;

import com.marketplace.domain.entity.ItemEntity;
import com.marketplace.domain.entity.UserEntity;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static ItemEntity createItemA(){
        return ItemEntity.builder()
                .setPrice("2000")
                .setDescription("Something interesting")
                .build();
    }


    public static ItemEntity createItemB(){
        return ItemEntity.builder()
                .setPrice("3000")
                .setDescription("Something interesting")
                .build();
    }

    public static ItemEntity createItemC(){
        return ItemEntity.builder()
                .setPrice("3000")
                .setDescription("Something interesting")
                .build();
    }

    public static UserEntity createUserA(){
        return UserEntity.builder()
                .setLogin("kash")
                .setPassword("1234567890")
                .build();
    }

    public static UserEntity createUserB(){
        return UserEntity.builder()
                .setLogin("evgen")
                .setPassword("1234567890")
                .build();
    }

    public static UserEntity createUserC(){
        return UserEntity.builder()
                .setLogin("yenot")
                .setPassword("1234567890")
                .build();
    }


}
