package eu.accesa.internship.epidemicrelief.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

public class DeliveredState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new CreatedState());
    }

    @Override
    public EnumPackageStatus getStatus() {
        return EnumPackageStatus.DELIVERED;
    }

}
