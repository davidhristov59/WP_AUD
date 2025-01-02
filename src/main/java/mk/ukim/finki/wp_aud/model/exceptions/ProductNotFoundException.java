package mk.ukim.finki.wp_aud.model.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " is not found");
    }
}
