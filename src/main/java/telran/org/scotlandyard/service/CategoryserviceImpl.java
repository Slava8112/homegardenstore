package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Category;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryserviceImpl implements CategoryService {


    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category UpdateCategory(Category category) {
        return null;
    }

    @Override
    public Category dlete(Category category) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return List.of();
    }
}
