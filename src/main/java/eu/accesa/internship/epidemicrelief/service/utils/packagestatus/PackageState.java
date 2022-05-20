package eu.accesa.internship.epidemicrelief.service.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;

public interface PackageState {
    void next(PackageStatus pkg);

    EnumPackageStatus getStatus();
}
