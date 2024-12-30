package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.listAll();
    }

    @Override
    public Category create(String name, String description) {
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        if(name == null || name.isEmpty() || description == null || description.isEmpty()){
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
