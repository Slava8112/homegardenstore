package telran.org.scotlandyard.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteDTO {
    private String productId;

    public FavoriteDTO() {}

    public FavoriteDTO(String productId) {
        this.productId = productId;
    }

}
