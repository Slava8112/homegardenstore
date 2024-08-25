package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Cartitems;

import java.util.List;

public interface CartItemsService {

    List<Cartitems> getAll();

    Cartitems add(String productsId, Cartitems cartitems);

    void deleteById(Long id);




}
