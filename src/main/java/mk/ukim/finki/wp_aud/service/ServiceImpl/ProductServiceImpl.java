package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp_aud.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud.repository.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.InMemoryProductRepository;
import mk.ukim.finki.wp_aud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository productRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;
    private final InMemoryCategoryRepository categoryRepository;

    public ProductServiceImpl(InMemoryProductRepository productRepository, InMemoryManufacturerRepository manufacturerRepository, InMemoryCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.listAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturerID) {

        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerID).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerID));
        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new CategoryNotFoundException(categoryID));

        return productRepository.save(name , price, quantity, category, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
