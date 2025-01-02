package mk.ukim.finki.wp_aud.service;

import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCarts(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productID);
}
