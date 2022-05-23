package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.PackageData;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Facade responsible for operations performed on {@link PackageData}
 */
public interface PackageFacade {
    /**
     * Get package by household id
     *
     * @param idHousehold id of the household
     * @return an Optional containing the last package of a household
     */
    Optional<PackageData> getPackageByIdHousehold(@NotNull Long idHousehold);

    /**
     * Delete package
     *
     * @param packageId id of the package to be deleted
     */
    void cancelPackage(@NotNull Long packageId);

    //String changeStatus(Optional<PackageData> packageData,Long idHousehold);
}
