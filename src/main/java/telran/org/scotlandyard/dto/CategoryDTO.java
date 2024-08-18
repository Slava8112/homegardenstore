package telran.org.scotlandyard.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDTO {
    private String categoryId;
    private String category;

    public CategoryDTO() {}

    public CategoryDTO(String categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

}
