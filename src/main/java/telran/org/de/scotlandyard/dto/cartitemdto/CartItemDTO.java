package telran.org.de.scotlandyard.dto.cartitemdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CartItemDTO {
    private Long productId;
    private int quantity;

    public CartItemDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
