package telran.org.scotlandyard.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Long categoryId;

    private double price;

    private String image;

    @Column(nullable = true)
    private double discountprice;

    public ProductDto(String name,
                      String description,
                      double price,
                      Long categoryId,
                      String image) {

        this.name = name;

        this.description = description;

        this.price = price;

        this.categoryId = categoryId;

        this.image = image;

    }
}