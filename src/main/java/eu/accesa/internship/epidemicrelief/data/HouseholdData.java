package eu.accesa.internship.epidemicrelief.data;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

import java.util.List;

public class HouseholdData {

    private Long id;
    private String representative;
    private String phone;
    private EnumPackageStatus status;
    private int numberOfPeople;
    private int numberOfChildren;
    private int numberOfVegans;
    private int numberOfNonVegans;

    private List<Package> packages;

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getNumberOfVegans() {
        return numberOfVegans;
    }

    public void setNumberOfVegans(int numberOfVegans) {
        this.numberOfVegans = numberOfVegans;
    }

    public int getNumberOfNonVegans() {
        return numberOfNonVegans;
    }

    public void setNumberOfNonVegans(int numberOfNonVegans) {
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
