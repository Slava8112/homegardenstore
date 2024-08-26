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
public class ProductCreateDto {

 private String name;

 private String description;

 private double price;

 private String category;

 private String image;
 @Column(nullable = true)
 private double discountprice;

// public ProductCreateDto(String name, String description, double price, String category, String image, double discountprice){
//  this.name = name;
//  this.description=description;
//  this.price = price;
//  this.category = category;
//  this.image = image;
//  this.discountprice = discountprice;
// }
}
