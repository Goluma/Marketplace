package com.marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Entity
@Table(name = "item_entity")
public class ItemEntity {

    @Id
    @GeneratedValue
    private UUID itemUuid;

    private String name;

    private String price;

    private String pathToImage;

    private String description;

    private UUID userUuid;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    private ItemEntity(ItemEntityBuilder itemBuilder){
        price = itemBuilder.price;
        description = itemBuilder.description;
    }

    public ItemEntity(){}

    public static ItemEntityBuilder builder(){
        return new ItemEntityBuilder();
    }

    public UUID getItemUuid(){
        return itemUuid;
    }

    public static final class ItemEntityBuilder{
        private String price;
        private String description;

        public ItemEntityBuilder setPrice(String price){
            this.price = price;
            return this;
        }

        public ItemEntityBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public ItemEntity build(){
            return new ItemEntity(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return itemUuid.equals(that.itemUuid)
                && price.equals(that.price)
                && description.equals(that.description)
                && pathToImage.equals(that.pathToImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemUuid, price, pathToImage, description);
    }
}
