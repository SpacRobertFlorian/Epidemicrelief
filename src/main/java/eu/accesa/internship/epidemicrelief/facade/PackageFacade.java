package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.PackageData;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface PackageFacade {
    Optional<PackageData> getPackageByIdHousehold(@NotNull Long idHousehold);

    void cancelPackage(@NotNull Long packageId);


    // String changeStatus(Optional<PackageData> packageData,Long idHousehold);
}
