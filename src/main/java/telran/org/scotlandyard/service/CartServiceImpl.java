package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Cart;
import telran.org.scotlandyard.repository.CartReposit;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

@Autowired
    private CartReposit cartReposit;

    @Override
    public Cart create(Cart cart) {
      Cart unit = (Cart) cartReposit.save(cart);
        return unit;
    }

    @Override
    public void delete(Long cart_id) {
        Cart unit = cartReposit.getById(cart_id);
        cartReposit.delete(unit);
    }
}
