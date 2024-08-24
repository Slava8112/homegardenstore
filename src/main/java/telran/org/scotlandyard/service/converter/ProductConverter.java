package telran.org.scotlandyard.service.converter;

import org.springframework.stereotype.Component;
import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.scotlandyard.dto.productdto.ProductDto;
import telran.org.scotlandyard.entity.Product;

@Component
public class ProductConverter implements Converter<Product, ProductDto, ProductCreateDto> {

    public Product toEntity(ProductCreateDto dto) {
        return null;
    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto();
    }
}
