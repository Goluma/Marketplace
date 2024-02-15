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
@Table(name = "item_entity")
public class ItemEntity {

    @Id
    @GeneratedValue
    private UUID itemUuid;

    private Double price;

    private List<String> listOfImageUrls;

    private String description;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public void setDescription(String str){
        description = str;
    }

    private ItemEntity(ItemEntityBuilder itemBuilder){
        price = itemBuilder.price;
        description = itemBuilder.description;
    }

    public UUID getItemUuid(){
        return itemUuid;
    }

    public static class ItemEntityBuilder{
        private Double price;
        private String description;

        public ItemEntityBuilder setPrice(Double price){
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
                && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemUuid, price, listOfImageUrls, description);
    }
}
