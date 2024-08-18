package telran.org.scotlandyard.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO {
    private String productId;
    private int quantity;

    public CartItemDTO() {}

    public CartItemDTO(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
