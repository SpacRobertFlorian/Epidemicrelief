package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;
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

import static eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus.NOT_CREATED;
import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequestMapping("/packages")
@EnableTransactionManagement
public class PackageController {
    private final HouseholdFacade householdFacade;
    private final PackageService packageService;

    private final PackageFacade packageFacade;
    @Value("${minim.stock.threshold}")
    private int threshold;
    @Value("${minim.date}")
    private int dateThreshold;

    @Autowired
    public PackageController(HouseholdFacade householdFacade, ProductFacade productFacade, PackageService packageService, PackageFacade packageFacade) {
        this.householdFacade = householdFacade;
        this.packageService = packageService;
        this.packageFacade = packageFacade;
    }

    @GetMapping
    public String getPackages(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return "package/packageList";
    }


    @GetMapping("/deliver/{idHousehold}")
    public String getPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> packageData = packageFacade.getPackageByIdHousehold(Long.valueOf(idHousehold));

        model.addAttribute("threshold", threshold);
        model.addAttribute("dateThreshold", dateThreshold);
        model.addAttribute("idHousehold", idHousehold);

        if (packageData.isEmpty()) {
            model.addAttribute("status", NOT_CREATED);
            return "package/createPackage";
        }

        model.addAttribute("package", packageData.get());
        return "package/createPackage";
    }

    //TODO FACADE PACKAGE refactoring this post mapping
//    @PostMapping("/deliver/{idHousehold}")
//    public String handlePackage(@PathVariable String idHousehold, Model model) {
//        Optional<PackageData> packageData = packageFacade.getPackageByIdHousehold(Long.valueOf(idHousehold));
//        return packageFacade.changeStatus(packageData, Long.valueOf(idHousehold));
//    }

    @PostMapping("/deliver/{idHousehold}")
    public String handlePackage(@PathVariable String idHousehold, Model model) {
        Optional<Package> packageOptional = packageService.getPackage(Long.valueOf(idHousehold));

        if (packageOptional.isEmpty() || packageOptional.get().getDeliveredDate() != null &&
                DAYS.between(LocalDate.now(), packageOptional.get().getDeliveredDate()) > dateThreshold) {
            packageService.createPackage(Long.valueOf(idHousehold));
            return "redirect:/packages/deliver/" + idHousehold;
        }

        Package packageStatus = packageOptional.get();
        packageStatus.setStatus(packageStatus.getStatus().next());
        packageService.updatePackage(packageStatus);

        if (EnumPackageStatus.READY.equals(packageStatus.getStatus())) {
            packageService.fillPackage(packageOptional.get());
            return "redirect:/packages/deliver/" + idHousehold;
        }

        if (EnumPackageStatus.DELIVERED.equals(packageStatus.getStatus())) {
            packageService.sendPackage(packageStatus);
            return "redirect:/packages/";
        }
        return "redirect:/packages/deliver/" + idHousehold;
        //return "redirect:/packages/" + packageStatus.getStatus() + "/" + idHousehold;
    }

    @GetMapping("/history")
    public String getPackageHistory(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return "package/packageHistory";
    }

    //TODO Cancel button
    @PostMapping("/cancel/{idHousehold}")
    public String cancelPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> packageData = packageFacade.getPackageByIdHousehold(Long.valueOf(idHousehold));

        if (packageData.isPresent() && packageData.get().getDeliveredDate() == null)
            packageFacade.cancelPackage(packageData.get().getId());
        return "redirect:/packages/";
    }
}
