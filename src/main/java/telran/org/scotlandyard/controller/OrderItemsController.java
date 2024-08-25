package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.OrderItem;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.OrderItemService;
import telran.org.scotlandyard.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/1v/orderitems")
@RequiredArgsConstructor
public class OrderItemsController {


    //private final ProductService productService;
    private final OrderItemService orderItemService;


    @GetMapping
    public List<OrderItem> list() {
        return orderItemService.getAllOrderItem();
    }

    @PostMapping("/id")
    public OrderItem add(@RequestParam String productsId,
                         @RequestBody OrderItem orderItem) {
        return orderItemService.addOrderItem(productsId, orderItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long orderItem) {
        orderItemService.deleteById(orderItem);
    }
}