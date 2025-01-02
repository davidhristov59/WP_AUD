package mk.ukim.finki.wp_aud.model.exceptions;

public class ManufacturerNotFoundException extends RuntimeException{

    public ManufacturerNotFoundException(Long id) {
        super("Manufacturer with ID " + id + " is not found");
    }
}
