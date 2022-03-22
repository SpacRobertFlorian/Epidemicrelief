package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/households")
public class HouseholdController {

    private final HouseholdFacade householdFacade;

    @Autowired
    public HouseholdController(HouseholdFacade householdFacade) {
        this.householdFacade = householdFacade;
    }

    @GetMapping
    public String getAllHouseholds(Model model) {
        model.addAttribute("households", householdFacade.getHouseholds());
        return "household/householdList";
    }

    @GetMapping("/new")
    public String getNewHouseholdForm() {
        return "household/addHousehold";
    }

    @PostMapping(value = "/save")
    public String addHousehold(@Valid HouseholdData household, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "household/addHousehold";
        }

        householdFacade.addHousehold(household);
        model.addAttribute("households", householdFacade.getHouseholds());
        return "redirect:/households";
    }

    @GetMapping("/edit/{id}")
    public String getUpdateHouseholdForm(@PathVariable("id") long id, Model model) {
        Optional<HouseholdData> household = householdFacade.getById(id);
        if (household.isEmpty()) {
            throw new IllegalArgumentException("No household exists for id " + id);
        }

        model.addAttribute("household", household.get());
        return "household/updateHousehold";
    }

    @PostMapping("/update")
    public String updateHousehold(@Valid HouseholdData household, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "household/updateHousehold";
        }

        householdFacade.updateHousehold(household);
        model.addAttribute("households", householdFacade.getHouseholds());
        return "redirect:/households";
    }

    @GetMapping("/delete/{id}")
    public String deleteHousehold(@PathVariable("id") long id) {
        householdFacade.deleteHousehold(id);
        return "redirect:/households";
    }

}
