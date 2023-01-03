package com.ecom.Service;

import com.ecom.Model.Cart;

public interface CartService {

    Cart addItemToCart( Integer id );

    Cart getCartInfo();

    Cart removeItemFromCart( Integer item );

    Double totalCartAmount();

    Cart increaseQuantity(Integer itemId);

    Cart decreaseQuantity(Integer itemId);

}

