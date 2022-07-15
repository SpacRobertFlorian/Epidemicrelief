package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "Product")
@Table(name = "product")
@NaturalIdCache
//@org.hibernate.annotations.Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "UUID cannot be null")
    private String uuid;
    @Column(unique = true)
    @NotBlank(message = "Name cannot be not blank")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters")
    private String name;

    @Column
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock should not be less then 0")
    private Long stock;
    @Column
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @OneToOne(cascade = CascadeType.ALL)
   // @Valid
    private Necessity necessity;


    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private List<PackageProducts> packages;

    @Column
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price should not be less then 0")
    private Double price;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    //@NotNull
    private String nameRo;

    @Column
   // @NotNull
    private String nameFr;


    public Product() {

    }

    public Product(String uuid, String name, Long stock, ProductCategory productCategory,
                   Double price, Currency currency, String nameRo, String nameFr) {
        this.uuid = uuid;
        this.name = name;
        this.stock = stock;
        this.productCategory = productCategory;
        this.price = price;
        this.currency = currency;
        this.nameRo = nameRo;
        this.nameFr = nameFr;
    }

    public String getNameRo() {
        return nameRo;
    }

    public void setNameRo(String nameRo) {
        this.nameRo = nameRo;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

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

    public Necessity getNecessity() {
        return necessity;
    }

    public void setNecessity(Necessity necessity) {
        this.necessity = necessity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<PackageProducts> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageProducts> packages) {
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


}
