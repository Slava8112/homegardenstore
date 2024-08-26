package telran.org.scotlandyard.service.converter;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.scotlandyard.dto.productdto.ProductDto;
import telran.org.scotlandyard.entity.Product;

@Component
public class ProductConverter implements Converter<Product, ProductDto, ProductCreateDto> {

    public Product toEntity(ProductCreateDto dto) {
        return new Product(null, dto.getName(), dto.getDescription(), dto.getPrice(), , dto.getImage(), dto.getDiscountprice(), null,null, dto.getCategory());

    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice()
                , product.getImage(), product.getDiscountprice(), product.getCreatedAt(), product.getUpdatedAt(), product.getCategory());
    }
}
