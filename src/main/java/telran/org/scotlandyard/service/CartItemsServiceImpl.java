package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Cartitems;
import telran.org.scotlandyard.repository.CartItemsReposit;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService{
    private final CartItemsReposit cartItemsReposit;

    @Override
    public List<Cartitems> getAll() {
        return cartItemsReposit.findAll();
    }

    @Override
    public Cartitems add(Cartitems cartitems) {
        return null;
    }

    @Override
    public Cartitems remove(Cartitems cartitems) {
        return null;
    }

    @Override
    public Cartitems update(Cartitems cartitems) {
        return null;
    }
}
