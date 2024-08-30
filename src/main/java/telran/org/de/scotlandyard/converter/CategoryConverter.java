//package telran.org.scotlandyard.service.converter;
//
//import org.springframework.stereotype.Component;
//import telran.org.scotlandyard.dto.categorydto.CategoryCreateDto;
//import telran.org.scotlandyard.dto.categorydto.CategoryDto;
//import telran.org.scotlandyard.dto.productdto.ProductCreateDto;
//import telran.org.scotlandyard.entity.Category;
//import telran.org.scotlandyard.entity.Product;
//
//@Component
//public class CategoryConverter implements Converter<Category, CategoryDto, CategoryCreateDto> {
//
//
//    @Override
//    public Category toEntity(CategoryCreateDto categoryCreateDto) {
//        return new Category();
//    }
//
//    @Override
//    public CategoryDto toDto(Category category) {
//        return new CategoryDto();
//    }
//}
//
package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.categorydto.CategoryCreateDto;
import telran.org.de.scotlandyard.dto.categorydto.CategoryDto;
import telran.org.de.scotlandyard.entity.Category;

@Component
public class CategoryConverter implements Converter<Category, CategoryDto, CategoryCreateDto> {

    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        return category;
    }

    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getId());
        dto.setCategory(category.getName());
        return dto;
    }
}
