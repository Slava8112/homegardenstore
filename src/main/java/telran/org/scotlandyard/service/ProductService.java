package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Long categoryId, Product product);

//    Product updateProduct(Product product);

    void delete(Product product);

    List<Product> getAllProduct();

    Product getById(Long productsId);

    //получение списка по запросу `category`, `minPrice`,
// `maxPrice`, `discount`, `sort`

        Product findByCategory(Long categoryId);
//
//    Product getPoductByminPrice();
//
//    Product getmaxPrice();




}
