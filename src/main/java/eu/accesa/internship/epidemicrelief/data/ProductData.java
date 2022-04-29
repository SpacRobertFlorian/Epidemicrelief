package eu.accesa.internship.epidemicrelief.data;


import eu.accesa.internship.epidemicrelief.enums.ProductCategory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductData {
    private Long id;
    private String name;
    private int stock;
    private ProductCategory productCategory;

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
