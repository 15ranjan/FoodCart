package com.foodcart.model;//package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class OrderItems extends BaseModel {

    @ManyToOne
    private FoodItem foodItem;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
