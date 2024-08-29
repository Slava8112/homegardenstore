package telran.org.scotlandyard.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long productId;
    private String name;

    private String description;

    //private Long categoryId;

    private double price;

    private String image;

    @Column(nullable = true)
    private double discountprice;

    public ProductDto(String name,
                      String description,
                      double price,
                      String image) {

        this.name = name;

        this.description = description;

        this.price = price;

        //this.categoryId = categoryId;

        this.image = image;

    }
}