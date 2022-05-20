package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

public class CreatedState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new ReadyState());
    }

    @Override
    public EnumPackageStatus getStatus() {
        return EnumPackageStatus.CREATED;
    }


}
