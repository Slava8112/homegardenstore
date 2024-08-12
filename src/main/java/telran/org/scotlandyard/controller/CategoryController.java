package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
   @Autowired
    public final CategoryService categoryService;

   @PostMapping
    public Category create(@RequestBody Category category){
       return categoryService.createCategory(category);
   }
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id){
       categoryService.delete(id);
   }
   @GetMapping
    public List<Category> getAll(){
       return categoryService.getAll();
   }
}
