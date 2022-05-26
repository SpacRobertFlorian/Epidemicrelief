package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
import eu.accesa.internship.epidemicrelief.model.DeliveryDateThreshold;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Optional;

import static eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus.NOT_CREATED;
import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequestMapping("/packages")
@EnableTransactionManagement
public class PackageController {
    private final HouseholdFacade householdFacade;
    private final PackageService packageService;
    private final PackageFacade packageFacade;
    private static final String PACKAGE_IST_URL = "package/packageList";
    private static final String REDIRECT_PACKAGES_URL = "redirect:/packages/";
    private static final String REDIRECT_PACKAGE_DELIVERY_URL = "redirect:/packages/deliver/";
    private static final String PACKAGE_HISTORY_URL = "package/packageHistory";
    private static final String CREATE_PACKAGE_URL = "package/createPackage";

    private final DeliveryDateThresholdRepository dateThreshold;
    @Value("${minim.stock.threshold}")
    private int stockThreshold;

    @Autowired
    public PackageController(HouseholdFacade householdFacade, PackageService packageService, PackageFacade packageFacade, DeliveryDateThresholdRepository dateThreshold) {
        this.householdFacade = householdFacade;
        this.packageService = packageService;
        this.packageFacade = packageFacade;

        this.dateThreshold = dateThreshold;
    }

    @GetMapping
    public String getPackages(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return PACKAGE_IST_URL;
    }

    @GetMapping("/deliver/{idHousehold}")
    public String getPackage(@PathVariable String idHousehold, Model model) {
        Optional<HouseholdData> household = householdFacade.getById(Long.parseLong(idHousehold));
        if (household.isEmpty()) {
            throw new CustomException("No household exists for id:" + idHousehold);
        }
        Optional<PackageData> packageData = packageFacade.getPackageByIdHousehold(Long.valueOf(idHousehold));

        model.addAttribute("threshold", stockThreshold);
        Optional<DeliveryDateThreshold> thresholdDelivery = dateThreshold.findById(1L);

        thresholdDelivery.ifPresent(deliveryDateThreshold -> model.addAttribute("dateThreshold",
                deliveryDateThreshold.getDeliveryDateThreshold()));
        model.addAttribute("idHousehold", idHousehold);

        if (packageData.isEmpty()) {
            model.addAttribute("status", NOT_CREATED);
            return CREATE_PACKAGE_URL;
        }

        model.addAttribute("package", packageData.get());
        return CREATE_PACKAGE_URL;
    }

    //TODO sa regandesc cum sa fac sa bag si facade in calcul
//    @PostMapping("/deliver/{idHousehold}")
//    public String handlePackage(@PathVariable String idHousehold, Model model) {
//
//        Optional<Package> packageOptional = packageService.getLastPackageByHouseholdId(Long.valueOf(idHousehold));
//        Optional<DeliveryDateThreshold> thresholdDelivery = dateThreshold.findById(1L);
//        if (packageOptional.isPresent()) {
//            Package packageStatus = packageOptional.get();
//            if (thresholdDelivery.isPresent()) {
//                DeliveryDateThreshold deliveryDateThreshold = thresholdDelivery.get();
//                if (DAYS.between(LocalDate.now(), packageStatus.getDeliveredDate()) > deliveryDateThreshold.getDeliveryDateThreshold()) {
//                    packageService.createPackage(Long.valueOf(idHousehold));
//                    return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
//                }
//            }
//            packageStatus.setStatus(packageStatus.getStatus().next());
//            packageService.updatePackage(packageStatus);
//
//            switch (packageStatus.getStatus()) {
//                case READY: {
//                    packageService.fillPackage(packageStatus);
//                    return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
//                }
//                case DELIVERED: {
//                    packageService.sendPackage(packageStatus);
//                    return REDIRECT_PACKAGES_URL;
//                }
//            }
//        } else {
//            packageService.createPackage(Long.valueOf(idHousehold));
//        }
//        return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
//    }

    @PostMapping("/deliver/{idHousehold}")
    public String handlePackage(@PathVariable String idHousehold, Model model) {

        Optional<Package> packageOptional = packageService.getLastPackageByHouseholdId(Long.valueOf(idHousehold));
        Optional<DeliveryDateThreshold> thresholdDelivery = dateThreshold.findById(1L);

        if (packageOptional.isEmpty() || packageOptional.get().getDeliveredDate() != null &&
                thresholdDelivery.isPresent() &&
                DAYS.between(LocalDate.now(), packageOptional.get().getDeliveredDate()) > thresholdDelivery.get().getDeliveryDateThreshold()) {

            packageService.createPackage(Long.valueOf(idHousehold));
            return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
        }

        Package packageStatus = packageOptional.get();
        packageStatus.setStatus(packageStatus.getStatus().next());
        packageService.updatePackage(packageStatus);

        if (EnumPackageStatus.READY.equals(packageStatus.getStatus())) {
            packageService.fillPackage(packageOptional.get());
            return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
        }

        if (EnumPackageStatus.DELIVERED.equals(packageStatus.getStatus())) {
            packageService.sendPackage(packageStatus);
            return REDIRECT_PACKAGES_URL;
        }
        return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
    }

    @GetMapping("/history")
    public String getPackageHistory(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return PACKAGE_HISTORY_URL;
    }

    @PostMapping("/cancel/{idHousehold}")
    public String cancelPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> packageData = packageFacade.getPackageByIdHousehold(Long.valueOf(idHousehold));

        if (packageData.isPresent() && packageData.get().getDeliveredDate() == null)
            packageFacade.cancelPackage(packageData.get().getId());
        return REDIRECT_PACKAGES_URL;
    }
}
