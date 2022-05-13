package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;
import java.util.List;

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
}

