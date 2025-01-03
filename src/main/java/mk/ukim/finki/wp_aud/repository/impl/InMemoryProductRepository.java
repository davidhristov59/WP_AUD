package mk.ukim.finki.wp_aud.repository.impl;

import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> listAll(){
        return DataHolder.products;
    }

    public Optional<Product> findByName(String name){
        return DataHolder.products
                .stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public Optional<Product> findById(Long id){
        return DataHolder.products
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category,Manufacturer manufacturer ){

        if(category == null || manufacturer == null){
            throw new IllegalArgumentException();
        }

        Product product = new Product(name, price, quantity, category, manufacturer);

        DataHolder.products.removeIf(p -> p.getName().equals(name));
        DataHolder.products.add(product);

        return Optional.of(product);
    }



}
