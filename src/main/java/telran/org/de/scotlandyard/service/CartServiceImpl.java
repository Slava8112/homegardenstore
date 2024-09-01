package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;

    @Override
    public void delete(Long cart_id) {
        Cart unit = cartRepository.getById(cart_id);
        cartRepository.delete(unit);
    }

}
