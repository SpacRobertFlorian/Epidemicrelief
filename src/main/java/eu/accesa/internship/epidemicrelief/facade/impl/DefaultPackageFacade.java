package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.PackageConverter;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.service.utils.packagestatus.PackageStatus;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class DefaultPackageFacade implements PackageFacade {

    private final PackageService packageService;
    private final PackageConverter packageConverter;
    private final PackageStatus packageStatus;

    public DefaultPackageFacade(PackageService packageService, PackageConverter packageConverter) {
        this.packageService = packageService;
        this.packageConverter = packageConverter;
        this.packageStatus = new PackageStatus();
    }

    @NotNull
    @Override
    public Optional<PackageData> getPackageByIdHousehold(Long idHousehold) {
        return packageService.getPackage(idHousehold).map(packageConverter::from);
    }

//    @Override
//    public String changeStatus(Optional<PackageData> packageData, Long idHousehold) {
//
//        if (packageData.isEmpty()) {
//            packageService.createPackage(idHousehold);
//        } else {
//            Optional<Package> aPackage = packageService.getPackage(packageData.get().getHousehold().getId());
//            packageStatus.setState(packageService.handlePackage(packageData));
//
//            if (aPackage.isPresent() && aPackage.get().getStatus().equals(EnumPackageStatus.DELIVERED)) {
//                aPackage.get().setCreatedDate(LocalDate.now());
//                packageService.updatePackage(aPackage.get());
//                return "redirect:/packages/";
//            }
//            packageStatus.nextState();
//            if (aPackage.isPresent()) {
//                //TODO FILL
//                aPackage.get().setStatus(packageStatus.getStatus());
//                packageService.updatePackage(aPackage.get());
//            }
//        }
//        return "redirect:/packages/deliver/" + idHousehold;
//    }

}
