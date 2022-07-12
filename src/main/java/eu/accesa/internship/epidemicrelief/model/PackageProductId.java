package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PackageProductId implements Serializable {

    @Column(name = "product_id")
    @NotNull(message = "Product id can't be null")
    private Long productId;

    @Column(name = "package_id")
    @NotNull(message = "Product id can't be null")
    private Long packageId;

    public PackageProductId() {

    }

    public PackageProductId(Long productId, Long packageId) {
        this.productId = productId;
        this.packageId = packageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageProductId that = (PackageProductId) o;
        return Objects.equals(productId, that.productId) && Objects.equals(packageId, that.packageId);
    }

    @Override
    public String toString() {
        return "PackageProductId{" +
                "productId=" + productId +
                ", packageId=" + packageId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, packageId);
    }
}
