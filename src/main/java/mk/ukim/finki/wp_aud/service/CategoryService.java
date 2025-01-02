package mk.ukim.finki.wp_aud.service;


import mk.ukim.finki.wp_aud.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

   Optional<Category> findById(Long id);
   List<Category> listCategories();
   Optional<Category> create(String name, String description);
   Optional<Category> update(String name, String description);
   void deleteById(Long id);
   List<Category> searchCategories(String text);
}
