package com.foodcart.controller;

import com.foodcart.model.FoodItem;
import com.foodcart.service.CartService;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // to add food items to the cart
    @PostMapping("/add")
    ResponseEntity<String> addToCart(@RequestBody List<FoodItem> foodItems){
        cartService.addToCart(foodItems);
        return ResponseEntity.ok().body("Successfully added FoodItems");
    }
    // to view food items in the cart
    @GetMapping("/cartDetails")
    ResponseEntity<List<FoodItem>> getCart(){
        List<FoodItem> foodItems = cartService.getCart();
        return ResponseEntity.ok().body(foodItems);
    }

    // to clear the cart
    @DeleteMapping("/clear")
    ResponseEntity<String> clearCart(){
        cartService.clearCart();
        return ResponseEntity.ok().body("Successfully cleared Cart");
    }
}
