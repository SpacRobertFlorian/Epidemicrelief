package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Necessity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PersonCategory personCategory;

    @Column
    @NotNull(message = "Quantity can't be null")
    @Min(value = 0, message = "Quantity should not be less then 0")
    private Long quantity;

    @OneToOne(mappedBy = "necessity", cascade = CascadeType.ALL)
    @Valid
    private Product product;

    public Necessity(PersonCategory personCategory, Long quantity, Product product) {
        this.personCategory = personCategory;
        this.quantity = quantity;
        this.product = product;
    }

    public Necessity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonCategory getPersonCategory() {
        return personCategory;
    }

    public void setPersonCategory(PersonCategory personCategory) {
        this.personCategory = personCategory;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
