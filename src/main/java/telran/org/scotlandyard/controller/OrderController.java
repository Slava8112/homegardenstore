package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.service.OrderSevice;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    public final OrderSevice orderSevice;

    @GetMapping("/OrderAll")
    public List<Order> list(){
        return orderSevice.getAllOrders();
    }

    @GetMapping("/OrderById")
    public Order getById(@PathVariable Long id){
        return orderSevice.findById(id);
    }

    @PostMapping("/OrderCreate")
    public Order create(@RequestBody Order order){

        return orderSevice.create(order);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long orderId){
        orderSevice.deleteById(orderId);
    }
}
