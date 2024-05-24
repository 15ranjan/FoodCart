package com.foodcart.service;

import com.foodcart.model.FoodItem;
import com.foodcart.model.Order;
import com.foodcart.model.OrderItems;
import com.foodcart.model.User;
import com.foodcart.repository.OrderRepository;
import com.foodcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

    public boolean placeOrder(String email){
        try{
            //1. find the user by its id whether this user exist or not in our repository
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if(!optionalUser.isPresent()){
                return false;
            }
            User user = optionalUser.get();
            //2. getting the cartItems added by the user
            List<FoodItem> foodItems = cartService.getCart();

            //3. creating a new Order
            Order order = new Order();
            order.setUser(user);

            //4. Converting foodItems added in the cart to OrderItems. Reason - To make sure that particular user will
            // definitely order those products added in the cart. Intention of the user is clear that he/she will place order
            List<OrderItems> orderItemsList = new ArrayList<>();
            for(FoodItem foodItem : foodItems){
                OrderItems orderItems = new OrderItems();
                orderItems.setOrder(order);
                orderItems.setFoodItem(foodItem);
                orderItemsList.add(orderItems);
            }
            order.setOrderItems(orderItemsList);

            orderRepository.save(order);

            cartService.clearCart();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    public List<Order> getOrdersForUser(Long userId){
        return orderRepository.findByUserId(userId);
    }

}
