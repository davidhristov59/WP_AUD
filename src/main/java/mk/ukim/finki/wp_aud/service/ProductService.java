package mk.ukim.finki.wp_aud.service;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturerID);
    Optional<Product> update(Long id, String name, Double price, Integer quantity, Long categoryID, Long manufacturerID);
    void deleteById(Long id);
    Page<Product> findPage(String name, Long categoryId, Long manufacturerId, Integer pageNum, Integer pageSize);
}
