package eu.accesa.internship.epidemicrelief.data;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class HouseholdData {

    private Long id;
    @Size(min = 2, max = 25, message
            = "Representative must be between 2 and 25 characters")
    @NotBlank(message = "Representative can't be null")
    @Pattern(regexp = "([a-zA-Z]+[ ]*)+", message = "Representative can't contain numbers")
    private String representative;
    @NotBlank(message = "Phone number can't be null")
    @Size(min = 10, max = 13, message
            = "Phone must be between 10 and 13 characters")
    private String phone;
    private EnumPackageStatus status;
    @Min(value = 1, message = "Number of people should not be less then 1")
    private Long numberOfPeople;
    @NotNull(message = "Number of children can't be null")
    @Min(value = 0, message = "Number of children should not be less then 0")
    private Long numberOfChildren;
    @NotNull(message = "Number of vegans can't be null")
    @Min(value = 0, message = "Number of vegans should not be less then 0")
    private Long numberOfVegans;
    @NotNull(message = "Number of non vegans can't be null")
    @Min(value = 0, message = "Number of non vegans should not be less then 0")
    private Long numberOfNonVegans;
    @NotBlank(message = "Email address can't be blank")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Contact address can't be blank")
    @Size(min = 5, max = 150, message
            = "Contact address must be between 5 and 150 characters")
    private String address;
    @Valid
    private List<Package> packages;

    public List<Package> getPackages() {
        return packages;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public Long getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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

    public EnumPackageStatus getStatus() {
        return status;
    }

    public void setStatus(EnumPackageStatus status) {
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
