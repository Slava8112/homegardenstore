package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.CartItems;

import java.util.List;

public interface CartItemsService {

    List<CartItems> getAll();

    CartItems add(Long productId, int quantity);

    void deleteById(Long id);
}
