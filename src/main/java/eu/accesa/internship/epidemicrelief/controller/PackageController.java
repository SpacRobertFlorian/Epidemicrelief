package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.packagestatus.PackageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/packages")
public class PackageController {
    private final HouseholdFacade householdFacade;
    private final ProductFacade productFacade;
    //TODO se pune statusul la toate, vreau doar la produsul ales
    private final PackageStatus packageStatus = new PackageStatus();

    private final PackageService packageService;
    @Value("${minim.stock.threshold}")
    private int threshold;

    //TODO tot un fel de threshold cu numarul de zile la care sa se trimita pachetele

    @Autowired
    public PackageController(HouseholdFacade householdFacade, ProductFacade productFacade, PackageService packageService) {
        this.householdFacade = householdFacade;
        this.productFacade = productFacade;
        this.packageService = packageService;
    }


    @GetMapping
    public String getPackages(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        model.addAttribute("status", packageStatus.getStatus());
        return "package/packageList";
    }

    @PostMapping("/create")
    public void fillPackage(Model model) {

    }

    @GetMapping("/deliver/{idHousehold}")
    public String getPackage(@PathVariable String idHousehold, Model model) {
        model.addAttribute("products", productFacade.getProducts());
        model.addAttribute("threshold", threshold);
        model.addAttribute("idHousehold", idHousehold);
        model.addAttribute("status", packageStatus.getStatus());

        return "package/createPackage";
    }

    @PostMapping("/deliver/{idHousehold}")
    public String createPackage(@PathVariable String idHousehold, Model model) {
        packageStatus.nextState();
        if( packageStatus.getStatus().equals("Delivered")){
            return "redirect:/packages/";
        }
        model.addAttribute("status", packageStatus.getStatus());
        return "redirect:/packages/deliver/" + idHousehold;
    }
}
