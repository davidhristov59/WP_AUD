package mk.ukim.finki.wp_aud.model;

import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Product> products;
    private ShoppingCartStatus shoppingCartStatus;

    public ShoppingCart() {
        this.id = (long) (Math.random() * 1000);
    }

    public ShoppingCart(User user) {
        this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.shoppingCartStatus = ShoppingCartStatus.CREATED;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShoppingCartStatus getShoppingCartStatus() {
        return shoppingCartStatus;
    }
}
