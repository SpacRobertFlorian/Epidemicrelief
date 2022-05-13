package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.model.Package;

import java.util.List;

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
    void fillPackage();
}
