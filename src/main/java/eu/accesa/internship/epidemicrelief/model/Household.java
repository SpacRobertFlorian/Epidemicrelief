package eu.accesa.internship.epidemicrelief.model;

import eu.accesa.internship.epidemicrelief.entity.HouseholdMembers;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String representative;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "household", cascade = CascadeType.ALL)
    private List<Package> packages;

    @Column
    private String phone;
    @Column
    private Long numberOfChildren;
    @Column
    private Long numberOfVegans;
    @Column
    private Long numberOfNonVegans;

    public Household(String representative, Long numberOfPeople, String phone, Long numberOfChildren, Long numberOfVegans, Long numberOfNonVegans) {
        this.representative = representative;
        this.numberOfPeople = numberOfPeople;
        this.phone = phone;
        this.numberOfChildren = numberOfChildren;
        this.numberOfVegans = numberOfVegans;
        this.numberOfNonVegans = numberOfNonVegans;
    }

    public Household() {

    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public Long getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Long numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Long getNumberOfVegans() {
        return numberOfVegans;
    }

    public void setNumberOfVegans(Long numberOfVegans) {
        this.numberOfVegans = numberOfVegans;
    }

    public Long getNumberOfNonVegans() {
        return numberOfNonVegans;
    }

    public void setNumberOfNonVegans(Long numberOfNonVegans) {
        this.numberOfNonVegans = numberOfNonVegans;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public Long getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public Optional<Package> getLatestPackage() {
        if (packages != null) {
            if (packages.size() > 0) {
                return Optional.of(packages.get(packages.size() - 1));
            }
//            packages.sort((x, y) -> x.getCreatedDate().compareTo(y.getCreatedDate()));
//            if (packages.size() > 0) {
//                return Optional.of(packages.get(0));
//            }
        }
        return Optional.empty();
    }
}

