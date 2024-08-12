package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Long categoryId, Product product);

    Product updateProduct(Product product);

    Product delete(Product product);

    List<Product> getAllProduct();
    List<Product> findByCategoryId(Long categoryId);



//    Product ge(Long id);


    //получение списка по запросу `category`, `minPrice`,
// `maxPrice`, `discount`, `sort`

    //    Product getProductByCategory(Category category);
//
//    Product getPoductByminPrice();
//
//    Product getmaxPrice();




}
