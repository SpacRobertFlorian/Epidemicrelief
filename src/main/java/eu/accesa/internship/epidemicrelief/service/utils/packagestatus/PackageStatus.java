package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

public class PackageStatus {
    private PackageState state = new OrderState();

    public PackageState getState() {
        return state;
    }

    public void setState(PackageState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public EnumPackageStatus getStatus() {
        return state.getStatus();
    }

}
