package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String representative;

    @Column(name = "number_of_people")
    private int numberOfPeople;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "household", cascade = CascadeType.ALL)
    private List<Package> packages;

    @Column
    private String phone;

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

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
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
        packages.sort((x, y) -> x.getCreatedDate().compareTo(y.getCreatedDate()));
        if (packages.size() > 0) {
            return Optional.of(packages.get(0));
        } else {
            return Optional.empty();
        }
    }
}

