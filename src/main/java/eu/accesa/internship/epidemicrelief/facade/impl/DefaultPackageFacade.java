package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.PackageConverter;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class DefaultPackageFacade implements PackageFacade {

    private final PackageService packageService;
    private final PackageConverter packageConverter;

    public DefaultPackageFacade(PackageService packageService, PackageConverter packageConverter) {
        this.packageService = packageService;
        this.packageConverter = packageConverter;
    }

    @NotNull
    @Override
    public @NotNull Optional<PackageData> getLastPackageByIdHousehold(Long idHousehold) {
        return packageService.getLastPackageByHouseholdId(idHousehold).map(packageConverter::from);
    }

    @Override
    public void cancelPackage(@NotNull Long packageId) {
        packageService.cancelPackage(packageId);
    }

    @Override
    public void updatePackage(PackageData packageStatus) {
        packageService.updatePackage(packageConverter.to(packageStatus));
    }

    @Override
    public void fillPackage(PackageData packageStatus) {
        packageService.fillPackage(packageConverter.to(packageStatus));
    }

    @Override
    public void sendPackage(PackageData packageStatus) {
        packageService.sendPackage(packageConverter.to(packageStatus));
    }

    @Override
    public void createPackage(String idHousehold) {
        packageService.createPackage(Long.valueOf(idHousehold));
    }
}
