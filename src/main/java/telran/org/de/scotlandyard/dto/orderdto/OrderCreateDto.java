package telran.org.de.scotlandyard.dto.orderdto;

import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.model.Status;

import java.util.List;

public record OrderCreateDto(List<CartItemCreateDto> items,
                             String deliveryAddress,
                             String deliveryMethod,
                             Status status){
}

//{
//        "deliveryAddress": "123 Main Street",
//        "deliveryMethod": "Courier",
//        "items": [
//        {
//        "productId": 1,
//        "quantity": 2
//        }
//        ]
//        }