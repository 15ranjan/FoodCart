package com.foodcart.model;//package models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "food_order") // Specify a different name for the table as Order is a reserved keyword in JPA
@Getter
@Setter
public class Order extends BaseModel{

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // Specify the mapped by attribute and cascade type
    private List<OrderItems> orderItems;

    private double totalPrice;
}
