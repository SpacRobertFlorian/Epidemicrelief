package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 2, max = 25, message
            = "Representative must be between 2 and 25 characters")
    @NotBlank(message = "Representative can't be null")
    @Pattern(regexp = "([a-zA-Z]+[ ]*)+", message = "Representative can't contain numbers")
    private String representative;

    @Column(name = "number_of_people")
    @Min(value = 1, message = "Number of people should not be less then 1")
    private Long numberOfPeople;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "household")
    @Valid
    private List<Package> packages;

    @Column
    @NotBlank(message = "Phone number can't be null")
    @Size(min = 10, max = 13, message
            = "Phone must be between 10 and 13 characters")
    private String phone;
    @Column
    @NotNull(message = "Number of children can't be null")
    @Min(value = 0, message = "Number of children should not be less then 0")
    private Long numberOfChildren;
    @Column
    @NotNull(message = "Number of vegans can't be null")
    @Min(value = 0, message = "Number of vegans should not be less then 0")
    private Long numberOfVegans;
    @Column
    @NotNull(message = "Number of non vegans can't be null")
    @Min(value = 0, message = "Number of non vegans should not be less then 0")
    private Long numberOfNonVegans;

    @Column
    @NotBlank(message = "Email address can't be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Column
    @NotBlank(message = "Contact address can't be blank")
    @Size(min = 5, max = 150, message
            = "Contact address must be between 5 and 150 characters")
    private String contactAddress;

    public Household(String representative, Long numberOfPeople, String phone, Long numberOfChildren, Long numberOfVegans, Long numberOfNonVegans, String email, String contactAddress) {
        this.representative = representative;
        this.numberOfPeople = numberOfPeople;
        this.phone = phone;
        this.numberOfChildren = numberOfChildren;
        this.numberOfVegans = numberOfVegans;
        this.numberOfNonVegans = numberOfNonVegans;
        this.email = email;
        this.contactAddress = contactAddress;
    }

    public Household() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
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

//    public Optional<Package> getLatestPackage() {
//        if (packages != null) {
//            if (packages.size() > 0) {
//                return Optional.of(packages.get(packages.size() - 1));
//            }
////            packages.sort((x, y) -> x.getCreatedDate().compareTo(y.getCreatedDate()));
////            if (packages.size() > 0) {
////                return Optional.of(packages.get(0));
////            }
//        }
//        return Optional.empty();
//    }

    public Optional<Package> getLatestPackage() {
        if (packages != null) {
            if (packages.size() > 0) {
                packages.sort((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate()));
                return Optional.of(packages.get(packages.size() - 1));
            }
        }
        return Optional.empty();
    }
}

