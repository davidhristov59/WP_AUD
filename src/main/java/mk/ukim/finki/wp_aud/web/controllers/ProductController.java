package mk.ukim.finki.wp_aud.web.controllers;

import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.model.Manufacturer;
import mk.ukim.finki.wp_aud.model.Product;
import mk.ukim.finki.wp_aud.service.CategoryService;
import mk.ukim.finki.wp_aud.service.ManufacturerService;
import mk.ukim.finki.wp_aud.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getProductPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long manufacturerId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model
    ) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        Page<Product> page = this.productService.findPage(name, categoryId, manufacturerId, pageNum, pageSize);
        model.addAttribute("page", page);
        model.addAttribute("manufacturers", this.manufacturerService.findAll());
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("name", name);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("manufacturerId", manufacturerId);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id){

        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProductPage(@PathVariable Long id, Model model){

        if(this.productService.findById(id).isPresent()){

            Product product = productService.findById(id).get();
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            List<Category> categories = categoryService.listCategories();

            model.addAttribute("product", product);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);

            return "add-product";
        }

        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductPage (Model model){

        List<Manufacturer> manufacturers = manufacturerService.findAll();
        List<Category> categories = categoryService.listCategories();

        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);

        return "add-product";
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer){

        if(id != null){
            this.productService.update(id, name, price, quantity, category, manufacturer);
        }
        else{
            this.productService.save(name, price, quantity, category, manufacturer);
        }

        return "redirect:/products";
    }


}
