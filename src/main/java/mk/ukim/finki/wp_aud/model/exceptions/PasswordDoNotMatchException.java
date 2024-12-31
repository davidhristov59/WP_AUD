package mk.ukim.finki.wp_aud.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Passwords do not match");
    }
}
