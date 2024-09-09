package telran.org.de.scotlandyard.dto.cartdto;

import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;

import java.util.HashSet;
import java.util.List;

public record CartCreateDto (Long userId, HashSet<CartItemCreateDto> cartItems){
    //
}
