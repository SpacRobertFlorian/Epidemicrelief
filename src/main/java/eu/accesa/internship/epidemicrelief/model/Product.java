package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String uuid;
    @Column(unique = true)
    private String name;
    @Column
    private int stock;
    @Column
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private List<Package> packages;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
