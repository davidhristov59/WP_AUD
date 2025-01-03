package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shop_users")
public class User {

    @Id
    private String username;

    private String password;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) //1 user ima poveke shopping carts
    private List<ShoppingCart> shoppingCarts;

    public User(){}

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
