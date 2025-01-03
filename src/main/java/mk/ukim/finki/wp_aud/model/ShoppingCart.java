package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne //poveke shopping carts za 1 user
    private User user;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
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
