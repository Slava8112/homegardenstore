package telran.org.de.scotlandyard.dto.orderdto;

import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.model.Status;

import java.util.List;

public record OrderCreateDto(List<CartItemCreateDto> items,
                             String deliveryAddress,
                             String deliveryMethod,
                             Status status) {
    //
}
