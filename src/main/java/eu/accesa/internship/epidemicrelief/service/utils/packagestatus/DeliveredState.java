package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

public class DeliveredState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        //Stop ca nu mai are unde sa mearga fra
    }

    @Override
    public EnumPackageStatus getStatus() {
        return EnumPackageStatus.DELIVERED;
    }

}
