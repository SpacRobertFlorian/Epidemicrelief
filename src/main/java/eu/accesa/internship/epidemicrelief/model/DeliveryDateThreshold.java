package eu.accesa.internship.epidemicrelief.model;

import javax.persistence.*;

@Entity
public class DeliveryDateThreshold {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer deliveryDateThreshold;

    public DeliveryDateThreshold(Integer i) {
        deliveryDateThreshold = i;
    }

    public DeliveryDateThreshold() {

    }

    public Integer getDeliveryDateThreshold() {
        return deliveryDateThreshold;
    }

    public void setDeliveryDateThreshold(Integer deliveryDateThreshold) {
        this.deliveryDateThreshold = deliveryDateThreshold;
    }
}
