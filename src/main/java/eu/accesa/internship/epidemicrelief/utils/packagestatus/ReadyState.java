package eu.accesa.internship.epidemicrelief.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

public class ReadyState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public EnumPackageStatus getStatus() {
        return EnumPackageStatus.READY;
    }
}
