package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;

@Entity(name = "PackageProducts")
@Table(name = "package_products")
public class PackageProducts {
    @EmbeddedId
    private PackageProductId id = new PackageProductId();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("packageId")
    private Package pack;

    @Column(name = "stock")
    private Long stock;

    public PackageProducts(Product product, Package pack, Long stock) {
        this.product = product;
        this.pack = pack;
        this.stock = stock;
    }

    public PackageProducts() {

    }

    public PackageProductId getId() {
        return id;
    }

    public void setId(PackageProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
