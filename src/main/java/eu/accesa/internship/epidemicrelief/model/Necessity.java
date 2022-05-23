package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;

import javax.persistence.*;

@Entity
public class Necessity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PersonCategory personCategory;

    @Column
    private Long quantity;

    @OneToOne(mappedBy = "necessity", cascade = CascadeType.ALL)
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
