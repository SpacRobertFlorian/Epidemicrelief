package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

import static eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus.NOT_CREATED;

public class OrderState implements PackageState {

    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new CreatedState());
    }

    @Override
    public EnumPackageStatus getStatus() {
        return NOT_CREATED;
    }
}
