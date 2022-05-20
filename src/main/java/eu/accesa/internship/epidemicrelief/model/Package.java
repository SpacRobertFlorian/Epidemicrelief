package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NaturalIdCache
@Table(name = "package")
//@org.hibernate.annotations.Cache(
//        usage = CacheConcurrencyStrategy.READ_WRITE
//)
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Household household;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PackageProducts> products;

    @Column
    private LocalDate deliveredDate;

    @Column
    private LocalDate createdDate;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumPackageStatus status = EnumPackageStatus.NOT_CREATED;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public List<PackageProducts> getProducts() {
        return products;
    }

    public void setProducts(List<PackageProducts> products) {
        this.products = products;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public EnumPackageStatus getStatus() {
        return status;
    }

    public void setStatus(EnumPackageStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

}
