package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.org.scotlandyard.service.ProductService;

@RestController
@RequestMapping("Products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


}
