package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

import static eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus.READY;

public class ReadyState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public EnumPackageStatus getStatus() {
        return READY;
    }
}
