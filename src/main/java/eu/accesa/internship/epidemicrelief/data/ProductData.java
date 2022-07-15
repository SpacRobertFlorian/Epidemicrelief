package eu.accesa.internship.epidemicrelief.data;


import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductData {
    private Long id;
    @NotBlank(message = "Name cannot be not blank")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
    private String name;
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock should not be less then 0")
    private Long stock;
    private String productCategory;
    private String uuid;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price should not be less then 0")
    private Double price;
    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
