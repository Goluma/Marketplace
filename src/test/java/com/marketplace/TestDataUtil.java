package com.marketplace;

import com.marketplace.domain.entity.ItemEntity;
import com.marketplace.domain.entity.UserEntity;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static ItemEntity createItemA(){
        return new ItemEntity
                .ItemEntityBuilder()
                .setPrice(2000d)
                .setDescription("Something interesting")
                .build();
    }

    public static ItemEntity createItemB(){
        return new ItemEntity
                .ItemEntityBuilder()
                .setPrice(3000d)
                .setDescription("Something interesting")
                .build();
    }

    public static ItemEntity createItemC(){
        return new ItemEntity
                .ItemEntityBuilder()
                .setPrice(3000d)
                .setDescription("Something interesting")
                .build();
    }

    public static UserEntity createUserA(){
        return new UserEntity.UserEntityBuilder()
                .setLogin("kash")
                .setPassword("1234567890")
                .build();
    }

    public static UserEntity createUserB(){
        return new UserEntity.UserEntityBuilder()
                .setLogin("evgen")
                .setPassword("1234567890")
                .build();
    }

    public static UserEntity createUserC(){
        return new UserEntity.UserEntityBuilder()
                .setLogin("yenot")
                .setPassword("1234567890")
                .build();
    }


}
