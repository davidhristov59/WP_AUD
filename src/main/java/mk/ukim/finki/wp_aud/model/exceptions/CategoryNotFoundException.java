package mk.ukim.finki.wp_aud.model.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super("Category with ID" + id + " is not found");
    }
}
