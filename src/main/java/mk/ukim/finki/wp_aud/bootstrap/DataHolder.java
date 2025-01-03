package mk.ukim.finki.wp_aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud.model.*;
import mk.ukim.finki.wp_aud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = null;
    public static List<User> users = null;
    public static List<Manufacturer> manufacturers = null;
    public static List<Product> products = null;
    public static List<ShoppingCart> shoppingCarts = null;

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ManufacturerRepository manufacturerRepository;

    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, UserRepository userRepository, ManufacturerRepository manufacturerRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){

        categories = new ArrayList<>();
        users = new ArrayList<>();
        manufacturers = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();

//        Category category1 = new Category("Sports", "Sports category");
//        Category category2 = new Category("Songs", "Songs category");
//        Category category3 = new Category("Food", "Food category");
//        Category category4 = new Category("Books", "Books category");
//        categories.add(category1);
//        categories.add(category2);
//        categories.add(category3);
//        categories.add(category4);
//
//        User user1 = new User("davidhristov123", "Davidh2003@", "David", "Hristov");
//        User user2 = new User("simona123", "SimonaD123@", "Simona", "Dimitrievska");
//        users.add(user1);
//        users.add(user2);
//
//        Manufacturer manufacturer = new Manufacturer("Nike", "USA");
//        Manufacturer manufacturer2 = new Manufacturer("Coca-Cola", "UK");
//        Manufacturer manufacturer3 = new Manufacturer("Literatura", "MK");
//        manufacturers.add(manufacturer);
//        manufacturers.add(manufacturer2);
//        manufacturers.add(manufacturer3);
//
//        Product product = new Product("Nike Air Max1", 200.0, 10, category1, manufacturer);
//        Product product2 = new Product("Coca-Cola 2L", 2.0, 40, category3, manufacturer2);
//        Product product3 = new Product("Meditations", 50.0, 60, category4, manufacturer3);
//
//        products.add(product);
//        products.add(product2);
//        products.add(product3);
    }
}
