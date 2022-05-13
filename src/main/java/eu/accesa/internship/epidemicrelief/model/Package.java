package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String packageStatus;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Household.class)
    private Household household;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @Column
    private Date deliveredDate;

}
