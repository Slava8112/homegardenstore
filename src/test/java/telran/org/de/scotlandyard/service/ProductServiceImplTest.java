package telran.org.de.scotlandyard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import telran.org.de.scotlandyard.entity.Category;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.exception.CategoryNotFoundException;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.repository.ProductRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        product.setCategory(category);
//        product.setCategoryId(1L);

    }

    @Test
    void addProduct_Test() {
        Product product = new Product();
//        when(categoryService.findById(anyLong())).thenReturn(category);
        when(repository.save(product)).thenReturn(product);
        Product createdProduct = productService.addProduct(product);
        assertNotNull(createdProduct);
//        assertEquals(product.getName(), createdProduct.getName());


        verify(repository, times(1)).save(product);
    }

    @Test
    void updateProduct_Test() {

        Long id = 1L;
       Product product1 = new Product();
        product1.setName("Update Name");
        Product product = new Product();
        //when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product1);
        assertNotNull(updatedProduct);
        assertEquals("Update Name", updatedProduct.getName());

        verify(repository).save(product);
        verify(repository).findById(id);

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

        when(repository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));
        List<Product> products = productService.getAllProduct();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_Test() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Product existProduct = productService.getById(1L);

        assertNotNull(existProduct);
        assertEquals(product.getName(), existProduct.getName());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByCategoryId() {
        when(repository.findAllByCategoryId(1L)).thenReturn(Collections.singletonList(product));
        List<Product> productsOfCategory = productService.findByCategoryId(category.getId());

        assertEquals(product.getName(), productsOfCategory.get(0).getName());
        verify(repository).findAllByCategoryId(1L);
    }
}