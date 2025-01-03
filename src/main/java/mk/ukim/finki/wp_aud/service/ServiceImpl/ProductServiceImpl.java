package mk.ukim.finki.wp_aud.service.ServiceImpl;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp_aud.model.exceptions.ProductNotFoundException;

import mk.ukim.finki.wp_aud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud.repository.jpa.ProductRepository;
import mk.ukim.finki.wp_aud.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static mk.ukim.finki.wp_aud.service.specifications.FieldFilterSpecification.filterContainsText;
import static mk.ukim.finki.wp_aud.service.specifications.FieldFilterSpecification.filterEquals;


import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
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

        Product product = new Product(name, price, quantity, category, manufacturer);

        return Optional.of(productRepository.save(product));
    }

    //koga imam update metod OBAVEZNO set za novite vrednosti
    @Override
    public Optional<Product> update(Long id,String name, Double price, Integer quantity, Long categoryID, Long manufacturerID) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerID).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerID));
        Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new CategoryNotFoundException(categoryID));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setManufacturer(manufacturer);
        product.setCategory(category);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findPage(String name, Long categoryId, Long manufacturerId, Integer pageNum, Integer pageSize) {
        Specification<Product> specification = Specification
                .where(filterContainsText(Product.class, "name", name))
                .and(filterEquals(Product.class, "category.id", categoryId))
                .and(filterEquals(Product.class, "manufacturer.id", manufacturerId));

        return this.productRepository.findAll(
                specification,
                PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Direction.DESC, "name"))
        );
    }

}
