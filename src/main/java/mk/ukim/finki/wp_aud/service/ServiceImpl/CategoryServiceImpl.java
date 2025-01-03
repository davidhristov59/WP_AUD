package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp_aud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> create(String name, String description) {
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return Optional.of(categoryRepository.save(category)); //BITNO!!!
    }

    @Override
    public Optional<Category> update(String name, String description) {
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        category.setName(name);
        category.setDescription(description);

        return Optional.of(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.findAllByNameLike(text);
    }
}
