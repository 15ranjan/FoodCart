package com.foodcart.service;

import com.foodcart.model.FoodItem;
import com.foodcart.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    private List<FoodItem> cart = new ArrayList<>();

    public void addToCart(List<FoodItem> foodItems) {
        for (FoodItem foodItem : foodItems) {
            cart.add(foodItem);
        }
        foodItemRepository.saveAll(cart);
    }

    public List<FoodItem> getCart() {

        return new ArrayList<>(cart);
    }

    public void clearCart(){
        cart.clear();
        foodItemRepository.deleteAll();
    }
}
