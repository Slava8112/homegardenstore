package telran.org.de.scotlandyard.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDto(
        Long productId,
        String name,
        String description,
        double price,
        Long categoryId,
        String image,
        Double discountPrice
) {
    public ProductDto(String name, String description, double price,Long categoryId, String image) {
        this(null, name, description, price,categoryId, image, null);
    }
}
        //this.categoryId = categoryId;
