package telran.org.de.scotlandyard.dto.orderdto;

import telran.org.de.scotlandyard.dto.cartitemdto.CartItemDTO;

import java.util.List;

public record OrderDTO(Long id,
                       List<CartItemDTO> items,
                       String deliveryAddress,
                       String deliveryMethod,
                       telran.org.de.scotlandyard.model.Status status) {
    //
}
