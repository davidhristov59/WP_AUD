package mk.ukim.finki.wp_aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = null;
    public static List<User> users = null;

    @PostConstruct
    public void init(){

        categories = new ArrayList<>();
        users = new ArrayList<>();

        Category category1 = new Category("Movies", "Movies category");
        Category category2 = new Category("Songs", "Songs category");

        categories.add(category1);
        categories.add(category2);

        User user1 = new User("davidhristov123", "Davidh2003@", "David", "Hristov");
        User user2 = new User("simona123", "SimonaD123@", "Simona", "Dimitrievska");

        users.add(user1);
        users.add(user2);
    }
}
