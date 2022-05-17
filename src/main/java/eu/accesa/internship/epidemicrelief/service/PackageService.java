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
     * Fill package wit products
     */
    void fillPackage(Package aPackage);

    Optional<Package> getPackage(Long id);

    void createPackage(Long idHousehold);

    void updatePackage(Package packageStatus);

    void sendPackage(Package packageStatus);
}
