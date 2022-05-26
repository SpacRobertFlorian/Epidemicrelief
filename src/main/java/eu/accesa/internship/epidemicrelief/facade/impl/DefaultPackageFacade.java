package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.PackageConverter;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
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
    public @org.jetbrains.annotations.NotNull Optional<PackageData> getPackageByIdHousehold(Long idHousehold) {

        return packageService.getLastPackageByHouseholdId(idHousehold).map(packageConverter::from);
    }

    @Override
    public void cancelPackage(@NotNull Long packageId) {
        packageService.cancelPackage(packageId);
    }


//    @Override
//    public String changeStatus(Optional<PackageData> packageData, Long idHousehold) {
//
//        if (packageData.isEmpty() || packageStatus.getStatus().equals(EnumPackageStatus.NOT_CREATED)) {
//            packageService.createPackage(idHousehold);
//            packageStatus.setState(packageService.handlePackage(packageData));
//        } else {
//            Optional<Package> aPackage = packageService.getPackage(packageData.get().getHousehold().getId());
//            packageStatus.setState(packageService.handlePackage(packageData));
//            packageStatus.nextState();
//
//
//            if (aPackage.isPresent()) {
//                aPackage.get().setStatus(packageStatus.getStatus());
//                if (aPackage.get().getStatus().equals(EnumPackageStatus.DELIVERED)) {
//                    aPackage.get().setDeliveredDate(LocalDate.now());
//
//                    packageStatus.nextState();
//                    packageService.updatePackage(aPackage.get());
//                    return "redirect:/packages/";
//                }
//
//                packageService.fillPackage(aPackage.get());
//                packageService.updatePackage(aPackage.get());
//            }
//        }
//        return "redirect:/packages/deliver/" + idHousehold;
//    }

}
