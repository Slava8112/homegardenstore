package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Cartitems;

import java.util.List;

public interface CartItemsService {

    List<Cartitems> getAll();

    Cartitems add(Cartitems cartitems);

    Cartitems remove(Cartitems cartitems);

    Cartitems update(Cartitems cartitems);
}
