package eu.accesa.internship.epidemicrelief.data;

import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

public class HouseholdData {

    private Long id;
    private String representative;
    private String phone;

    private EnumPackageStatus status;

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
