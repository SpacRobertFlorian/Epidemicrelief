package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.PackageRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;

    @Autowired
    public DefaultPackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public void fillPackage() {
    }
}
