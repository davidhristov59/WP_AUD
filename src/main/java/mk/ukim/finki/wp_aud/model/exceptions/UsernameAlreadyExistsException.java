package mk.ukim.finki.wp_aud.model.exceptions;


public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super("Username " + message + " already exists");
    }
}
