package eu.accesa.internship.epidemicrelief.utils.packagestatus;

import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;

public interface PackageState {
    void next(PackageStatus pkg);

    EnumPackageStatus getStatus();
}
