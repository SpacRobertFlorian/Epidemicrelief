package eu.accesa.internship.epidemicrelief.entity.visitor.model;

public class ProductNecessity {
    private String uuid;
    private int stock;

    public ProductNecessity(String uuid, int stock) {
        this.uuid = uuid;
        this.stock = stock;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductNecessity{" +
                "uuid='" + uuid + '\'' +
                ", stock=" + stock +
                '}';
    }
}
