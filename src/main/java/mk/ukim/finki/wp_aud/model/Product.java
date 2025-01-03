package mk.ukim.finki.wp_aud.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    @ManyToOne //poveke produkti vo 1 kategorija
    private Category category;

    @ManyToOne //poveke produkti da imaat 1 manufacturer
    private Manufacturer manufacturer;

    public Product( String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
