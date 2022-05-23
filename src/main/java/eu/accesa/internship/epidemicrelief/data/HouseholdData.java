package eu.accesa.internship.epidemicrelief.data;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

import java.util.List;

public class HouseholdData {

    private Long id;
    private String representative;
    private String phone;
    private EnumPackageStatus status;
    private Long numberOfPeople;
    private Long numberOfChildren;
    private Long numberOfVegans;
    private Long numberOfNonVegans;

    private List<Package> packages;

    public List<Package> getPackages() {
        return packages;
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
