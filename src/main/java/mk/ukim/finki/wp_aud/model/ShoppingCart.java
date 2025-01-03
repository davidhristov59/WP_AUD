package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp_aud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp_aud.service.ShoppingCartService;

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

    public ShoppingCart(){}

    public ShoppingCart(User user) {
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
