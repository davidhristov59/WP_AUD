package mk.ukim.finki.wp_aud.model.exceptions;

public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(Long productID, String username) {
        super("Product with id " + productID + " already exists in shopping cart for user with username " + username);
    }
}
