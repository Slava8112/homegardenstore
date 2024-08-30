package telran.org.de.scotlandyard.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateDto {

    private String name;

    private String description;

    private double price;

   private Long categoryId;

    private String image;

    public ProductCreateDto(String name,
                            String description,
                            double price,
                            //Long categoryId,
                            String image) {
        this.name = name;

        this.description = description;

        this.price = price;

//        this.categoryId = categoryId;

        this.image = image;
    }
}
