package mk.ukim.finki.wp_aud.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories = null;

    @PostConstruct
    public void init(){

        categories = new ArrayList<>();

        Category category1 = new Category("Movies", "Movies category");
        Category category2 = new Category("Songs", "Songs category");

        categories.add(category1);
        categories.add(category2);
    }
}
