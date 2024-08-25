package telran.org.scotlandyard.service.converter;

import org.springframework.stereotype.Component;
import telran.org.scotlandyard.dto.categorydto.CategoryCreateDto;
import telran.org.scotlandyard.dto.categorydto.CategoryDto;
import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;

@Component
public class CategoryConverter implements Converter<Category, CategoryDto, CategoryCreateDto> {


    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        return new Category();
    }

    @Override
    public CategoryDto toDto(Category category) {
        return new CategoryDto();
    }
}

