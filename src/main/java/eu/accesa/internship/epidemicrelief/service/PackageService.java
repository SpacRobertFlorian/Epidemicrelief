package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.model.Package;

import java.util.List;
import java.util.Optional;

public interface PackageService {

    /**
     * Get a list of Packages
     *
     * @return list of all packages
     */
    List<Package> getAllPackages();

    /**
     * Fill package with products
     *
     */
    void fillPackage(Package aPackage);

    /**
     * Get a package searched by id
     * @param id id of the product to be search by
     * @return an Optional containing the Package
     */
    Optional<Package> getLastPackageByHouseholdId(Long id);

    /**
     * Create a package for a specific household
     * @param idHousehold id of a specific household
     */
    void createPackage(Long idHousehold);

    /**
     * Updates a package
     * @param pack a {@link Package}
     */
    void updatePackage(Package pack);

    /**
     * Set delivered status of a package and save it
     * @param pack a {@link Package} to be delivered
     */
    void sendPackage(Package pack);

    /**
     * Delete a package
     * @param packageId id of a package to be deleted
     */
    void cancelPackage(Long packageId);

    //PackageState handlePackage(Optional<PackageData> packageData);

}
