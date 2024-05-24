package com.foodcart.controller;

import com.foodcart.model.Order;
import com.foodcart.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placementOfOrder(@RequestParam String email){
        boolean success = orderService.placeOrder(email);
        if(success){
            return ResponseEntity.ok().body("Successfully placed order");
        }else{
            return ResponseEntity.badRequest().body("Unable to place order, user may not exist");
        }
    }

    @GetMapping("/getOrder")
    public List<Order> getOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/userOrder/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        List<Order> ordersList = orderService.getOrdersForUser(userId);

        if(!ordersList.isEmpty()){
            return ResponseEntity.ok().body(ordersList);
        }else {
            return ResponseEntity.ok().build();
        }
    }
}
