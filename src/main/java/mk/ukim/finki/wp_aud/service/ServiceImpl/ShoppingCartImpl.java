package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp_aud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wp_aud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp_aud.repository.jpa.ProductRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(shoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCarts(String username) {

        return shoppingCartRepository.findByUserUsernameAndShoppingCartStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));

                    ShoppingCart shoppingCart = new ShoppingCart(user);

                    return shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productID) {

        ShoppingCart shoppingCart = this.getActiveShoppingCarts(username);

        Product product = productRepository.findById(productID).orElseThrow(() -> new ProductNotFoundException(productID));

        if(shoppingCart.getProducts().stream().filter(s -> s.getId().equals(productID)).collect(Collectors.toList()).size() > 0){
            throw new ProductAlreadyInShoppingCartException(productID, username);
        }

        shoppingCart.getProducts().add(product);

        return shoppingCartRepository.save(shoppingCart);
    }
}
