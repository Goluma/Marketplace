package com.marketplace.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    public UserEntity(){}

    private UserEntity(UserEntityBuilder builder){
        email = builder.login;
        password = builder.password;
        nickName = builder.nickName;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ItemEntity> listOfUserItems;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public void setEmail(String email){
        this.email = email;
    }

    public UUID getUserUuid(){
        return userUuid;
    }

    public static UserEntityBuilder builder(){
        return new UserEntityBuilder();
    }

    public static final class UserEntityBuilder{
        private String login;
        private String password;

        private String nickName;

        public UserEntityBuilder setLogin(String login){
            this.login = login;
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
                && password.equals(that.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userUuid, email, password);
    }
}
