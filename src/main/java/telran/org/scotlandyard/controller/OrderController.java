package telran.org.scotlandyard.controller;

import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.service.OrderSevice;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    public final OrderSevice orderSevice;

    @GetMapping
    public List<Order> list(){
        return orderSevice.getAll();
    }
//
//    @GetMapping
//    public Order getById(Long orderId){
//        return orderSevice.findById(orderId);
//    }
    @PostMapping
    public Order create(@RequestBody Order order){
        return orderSevice.create(order);
    }
}
