package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.facade.PackageFacade;
import eu.accesa.internship.epidemicrelief.model.DeliveryDateThreshold;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
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
    private final PackageFacade packageFacade;
    private static final String PACKAGE_IST_URL = "package/packageList";
    private static final String REDIRECT_PACKAGES_URL = "redirect:/packages/";
    private static final String PACKAGE_HISTORY_URL = "package/packageHistory";
    private static final String CREATE_PACKAGE_URL = "package/createPackage";

    private static final String REDIRECT_PACKAGE_DELIVERY_URL = "redirect:/packages/deliver/";
    private final DeliveryDateThresholdRepository dateThreshold;
    @Value("${minim.stock.threshold}")
    private int stockThreshold;

    @Autowired
    public PackageController(HouseholdFacade householdFacade, PackageFacade packageFacade, DeliveryDateThresholdRepository dateThreshold) {
        this.householdFacade = householdFacade;
        this.packageFacade = packageFacade;
        this.dateThreshold = dateThreshold;
    }

    @GetMapping
    public String getPackages(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return PACKAGE_IST_URL;
    }

    @GetMapping("/deliver/{idHousehold}")
    public String createPackage(@PathVariable String idHousehold, Model model) {
        Optional<HouseholdData> household = householdFacade.getById(Long.parseLong(idHousehold));
        if (household.isEmpty()) {
            throw new CustomException("No household exists for id:" + idHousehold);
        }
        Optional<PackageData> packageData = packageFacade.getLastPackageByIdHousehold(Long.valueOf(idHousehold));

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

    @PostMapping("/deliver/{idHousehold}/fill")
    public String fillPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> lastPackage = packageFacade.getLastPackageByIdHousehold(Long.valueOf(idHousehold));
        if (lastPackage.isPresent()) {
            PackageData packageStatus = lastPackage.get();
            packageStatus.setStatus(packageStatus.getStatus().next());
            packageFacade.updatePackage(packageStatus);
            packageFacade.fillPackage(packageStatus);
        }
        return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
    }

    @PostMapping("/deliver/{idHousehold}/send")
    public String sendPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> lastPackage = packageFacade.getLastPackageByIdHousehold(Long.valueOf(idHousehold));
        if (lastPackage.isPresent()) {
            PackageData packageStatus = lastPackage.get();
            packageStatus.setStatus(packageStatus.getStatus().next());
            packageFacade.updatePackage(packageStatus);
            packageFacade.sendPackage(packageStatus);
        }
        return REDIRECT_PACKAGES_URL;
    }

    @PostMapping("/deliver/{idHousehold}")
    public String handlePackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> lastPackage = packageFacade.getLastPackageByIdHousehold(Long.valueOf(idHousehold));
        Optional<DeliveryDateThreshold> thresholdDelivery = dateThreshold.findById(1L);
        if (lastPackage.isEmpty() || lastPackage.get().getDeliveredDate() != null &&
                thresholdDelivery.isPresent() &&
                DAYS.between(LocalDate.now(), lastPackage.get().getDeliveredDate()) > thresholdDelivery.get().getDeliveryDateThreshold()) {

            packageFacade.createPackage(idHousehold);
            return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
        }

        model.addAttribute("lastPackage", lastPackage);
        model.addAttribute("dateThreshold", thresholdDelivery);
        lastPackage.ifPresent(packageData -> model.addAttribute("dateDiff", DAYS.between(LocalDate.now(), packageData.getDeliveredDate())));

        return REDIRECT_PACKAGE_DELIVERY_URL + idHousehold;
    }

    @GetMapping("/history")
    public String getPackageHistory(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return PACKAGE_HISTORY_URL;
    }

    @PostMapping("/cancel/{idHousehold}")
    public String cancelPackage(@PathVariable String idHousehold, Model model) {
        Optional<PackageData> packageData = packageFacade.getLastPackageByIdHousehold(Long.valueOf(idHousehold));

        if (packageData.isPresent() && packageData.get().getDeliveredDate() == null)
            packageFacade.cancelPackage(packageData.get().getId());
        return REDIRECT_PACKAGES_URL;
    }
}
