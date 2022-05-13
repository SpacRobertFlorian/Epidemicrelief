package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.enums.PackageStatus;
import eu.accesa.internship.epidemicrelief.enums.ProductCategory;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PackageStatus packageStatus;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Household.class)
    private Household household;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @Column
    private Date deliveredDate;

}
