package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PackageService {

    /**
     * Get a list of Packages
     *
     * @return list of all packages
     */
    @NonNull
    List<Package> getAllPackages();

    /**
     * Fill package with products
     */
    void fillPackage(@NotNull Package aPackage);

    /**
     * Get a package searched by id
     *
     * @param id id of the product to be search by
     * @return an Optional containing the Package
     */
    @NonNull
    Optional<Package> getLastPackageByHouseholdId(Long id);

    /**
     * Create a package for a specific household
     *
     * @param idHousehold id of a specific household
     */
    void createPackage(Long idHousehold);

    /**
     * Updates a package
     *
     * @param pack a {@link Package}
     */
    void updatePackage(@NotNull Package pack);

    /**
     * Set delivered status of a package and save it
     *
     * @param pack a {@link Package} to be delivered
     */
    void sendPackage(@NotNull Package pack);

    /**
     * Delete a package
     *
     * @param packageId id of a package to be deleted
     */
    void cancelPackage(Long packageId);
}
