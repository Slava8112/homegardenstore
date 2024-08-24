package telran.org.scotlandyard.dto.productdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductCreateDto {

 private String name;

 private String description;

 private double price;

 private Long category;

 private String image;
}
