package eu.accesa.internship.epidemicrelief.data;

import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

import java.time.LocalDate;
import java.util.List;

public class PackageData {

    private Long id;
    private HouseholdData household;
    private List<PackageProducts> products;
    private LocalDate deliveredDate;
    private LocalDate createdDate;
    private EnumPackageStatus status;
    private Long dateDiff;

    public Long getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(Long dateDiff) {
        this.dateDiff = dateDiff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HouseholdData getHousehold() {
        return household;
    }

    public void setHousehold(HouseholdData household) {
        this.household = household;
    }

    public List<PackageProducts> getProducts() {
        return products;
    }

    public void setProducts(List<PackageProducts> products) {
        this.products = products;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public EnumPackageStatus getStatus() {
        return status;
    }

    public void setStatus(EnumPackageStatus status) {
        this.status = status;
    }
}
