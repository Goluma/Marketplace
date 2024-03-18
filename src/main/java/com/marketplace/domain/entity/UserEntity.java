package com.marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "marketplace_user")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID userUuid;

    private String email;

    private String nickName;

    private String password;

    private Set<String> roles;

    public UserEntity(){}

    private UserEntity(UserEntityBuilder builder){
        email = builder.email;
        password = builder.password;
        nickName = builder.nickName;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userUuid")
    private List<ItemEntity> listOfUserItems;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public static UserEntityBuilder builder(){
        return new UserEntityBuilder();
    }

    public static final class UserEntityBuilder{
        private String email;
        private String password;

        private String nickName;

        public UserEntityBuilder setLogin(String login){
            this.email = email;
            return this;
        }

        public UserEntityBuilder setPassword(String password){
            this.password = password;
            return this;
        }
        public UserEntityBuilder setNickName(String nickName){
            this.nickName = nickName;
            return this;
        }

        public UserEntity build(){
            return new UserEntity(this);
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != getClass()) return false;

        UserEntity that = (UserEntity) o;
        return userUuid.equals(that.userUuid)
                && email.equals(that.email)
                && password.equals(that.password)
                && nickName.equals(that.nickName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userUuid, email, password, nickName);
    }
}