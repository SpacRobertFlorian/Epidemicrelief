package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus.NOT_CREATED;
import static java.time.temporal.ChronoUnit.DAYS;

@Controller
@RequestMapping("/packages")
public class PackageController {
    private final HouseholdFacade householdFacade;
    private final ProductFacade productFacade;
    private final PackageService packageService;
    @Value("${minim.stock.threshold}")
    private int threshold;
    @Value("${minim.date}")
    private int dateThreshold;

    @Autowired
    public PackageController(HouseholdFacade householdFacade, ProductFacade productFacade, PackageService packageService) {
        this.householdFacade = householdFacade;
        this.productFacade = productFacade;
        this.packageService = packageService;
    }

    @GetMapping
    public String getPackages(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return "package/packageList";
    }


    @GetMapping("/deliver/{idHousehold}")
    public String getPackage(@PathVariable String idHousehold, Model model) {
        Optional<Package> packageOptional = packageService.getPackage(Long.valueOf(idHousehold));

        model.addAttribute("products", productFacade.getProducts());
        model.addAttribute("threshold", threshold);
        model.addAttribute("dateThreshold", dateThreshold);
        model.addAttribute("idHousehold", idHousehold);
        if (packageOptional.isPresent()) {
            Package packageStatus = packageOptional.get();
            model.addAttribute("status", packageStatus.getStatus().toString());
            model.addAttribute("difDate", DAYS.between(LocalDate.now(), packageOptional.get().getDeliveredDate()));

        } else {
            model.addAttribute("status", NOT_CREATED.toString());
            model.addAttribute("difDate", 0);
        }

        //TODO problema cand un household nu are pachet
        return "package/createPackage";
    }

    @PostMapping("/deliver/{idHousehold}")
    public String deliverPackage(@PathVariable String idHousehold, Model model) {
        Optional<Package> packageOptional = packageService.getPackage(Long.valueOf(idHousehold));
        if (packageOptional.isEmpty()) {
            packageService.createPackage(Long.valueOf(idHousehold));
            return "redirect:/packages/deliver/" + idHousehold;
        }
        Package packageStatus = packageOptional.get();
        packageStatus.setStatus(packageStatus.getStatus().next());
        packageService.updatePackage(packageStatus);

        if (EnumPackageStatus.READY.equals(packageStatus.getStatus())) {
            return "redirect:/packages/deliver/" + idHousehold;
        }

        if (EnumPackageStatus.DELIVERED.equals(packageStatus.getStatus())) {
            packageService.sendPackage(packageStatus);
            return "redirect:/packages/";
        }
        return "redirect:/packages/deliver/" + idHousehold;
        //return "redirect:/packages/" + packageStatus.getStatus() + "/" + idHousehold;
    }
}
