package telran.org.de.scotlandyard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class OrderDTO {
    private List<CartItemDTO> items;
    private String deliveryAddress;
    private String deliveryMethod;

    public OrderDTO() {}

    public OrderDTO(List<CartItemDTO> items, String deliveryAddress, String deliveryMethod) {
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.deliveryMethod = deliveryMethod;
    }

}
