package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;

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
    @NotNull
    Optional<PackageData> getLastPackageByIdHousehold(@NotNull Long idHousehold);

    /**
     * Delete package
     *
     * @param packageId id of the package to be deleted
     */
    void cancelPackage(@NotNull Long packageId);

    void updatePackage(PackageData packageStatus);

    void fillPackage(PackageData packageStatus);

    void sendPackage(PackageData packageStatus);

    void createPackage(String idHousehold);

}
