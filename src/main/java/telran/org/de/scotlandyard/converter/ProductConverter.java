package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.de.scotlandyard.dto.productdto.ProductDto;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.service.CategoryService;

@Component
public class ProductConverter implements Converter<Product, ProductDto, ProductCreateDto> {

    private final CategoryService categoryService;
    public ProductConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public Product toEntity(ProductCreateDto dto) {
        Category category = categoryService.findById(dto.getCategoryId());
        return new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                category,
                //dto.getCategoryId(),
                dto.getImage()
        );
    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId(),
                //product.getCategoryId()
                product.getImage(),
                product.getDiscountPrice()
        );
    }
}

