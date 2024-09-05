package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.repository.ProductRepository;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;
    private Category category;


    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);

        product = new Product();
        product.setId(1L);
        product.setName("Name");
        product.setDescription("Description");
        product.setPrice(1000);
        product.setCategory(category);

        product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setPrice(1000);
        product.setCategoryId(1L);

    }

    @Test
    void addProduct_Test() {
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(repository.save(any(Product.class))).thenReturn(product);
        Product createProduct = productService.addProduct(product);
        assertNotNull(createProduct);
        assertEquals(product.getName(), createProduct.getName());

        verify(categoryService).findById(anyLong());
        verify(repository).save(any(Product.class));
    }

    @Test
    void updateProduct_Test() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(product);
        product.setName("Update Named");
        Product updatedProduct = productService.updateProduct(product);

        assertNotNull(updatedProduct);
        assertEquals("Update Named", updatedProduct.getName());

        verify(repository).findById(anyLong());
        verify(repository).save(any(Product.class));

    }

    @Test
    void deleteById_Test() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        doNothing().when(repository).delete(any(Product.class));
        productService.deleteById(1L);
        verify(repository).findById(anyLong());
        verify(repository).delete(any(Product.class));
    }

    @Test
    void getAllProduct_Test() {
        when(repository.findAll(any(Sort.class))).thenReturn(Collections.singletonList(product));
        List<Product> products = productService.getAllProduct();

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(product.getName(), products.get(0).getName());
        verify(repository).findAll(Sort.by("name"));
    }

    @Test
    void getById_Test() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Product existProduct = productService.getById(1L);

        assertNotNull(existProduct);
        assertEquals(product.getName(), existProduct.getName());

        verify(repository).findById(1L);
    }

    @Test
    void findByCategoryId() {
when(repository.findAllByCategoryId(1L)).thenReturn(Collections.singletonList(product));
List<Product> productsOfCategory = productService.findByCategoryId(category.getId());

assertEquals(product.getName(), productsOfCategory.get(0).getName());
verify(repository).findAllByCategoryId(1L);
    }
}