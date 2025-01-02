package mk.ukim.finki.wp_aud.repository;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream()
                .filter(s -> s.getUser().getUsername().equals(username) &&
                        s.getShoppingCartStatus().equals(status)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        DataHolder.shoppingCarts.removeIf(s -> s.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));

        DataHolder.shoppingCarts.add(shoppingCart);

        return shoppingCart;
    }

}
