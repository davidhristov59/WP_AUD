package mk.ukim.finki.wp_aud.model.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long id) {
        super("Shopping cart with ID " + id + " is not found");
    }
}
