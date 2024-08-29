package telran.org.scotlandyard.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteDTO {
    private Long productId;

    public FavoriteDTO() {}

    public FavoriteDTO(Long productId) {
        this.productId = productId;
    }

}
