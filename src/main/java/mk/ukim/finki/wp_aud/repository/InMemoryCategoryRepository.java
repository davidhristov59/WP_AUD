package mk.ukim.finki.wp_aud.repository;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {

    public List<Category> listAll(){
        return DataHolder.categories;
    }

    public Category save(Category category){

        //ako postoi kategorijata, izbrisi ja i dodavi nova
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));

        DataHolder.categories.add(category);

        return category;
    }

    public Optional<Category> findAllByName(String name){
        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream()
                .filter(c -> c.getName().contains(text) ||
                        c.getDescription().contains(text))
                .toList();
    }

    public void delete(String name){
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }

}
