package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.service.OrderSevice;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
   @Autowired
    public final OrderSevice orderSevice;

    @GetMapping
    public List<Order> list(){
        return orderSevice.getAll();
    }

//    @GetMapping
//    public Order getById(Long orderId){
//       Order unit = orderSevice.getById(orderId);
//        return unit;
//    }
    @PostMapping
    public Order create(@RequestBody Order order){
        return orderSevice.create(order);
    }


}
