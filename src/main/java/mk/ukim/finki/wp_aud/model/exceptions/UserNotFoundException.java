package mk.ukim.finki.wp_aud.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("User " + username + " is not found");
    }
}
