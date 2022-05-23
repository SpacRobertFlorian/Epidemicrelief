package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.HouseholdConverter;
import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.facade.HouseholdFacade;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.service.HouseholdService;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultHouseholdFacade implements HouseholdFacade {

    private final HouseholdService householdService;
    private final HouseholdConverter householdConverter;

    public DefaultHouseholdFacade(HouseholdService householdService, HouseholdConverter householdConverter) {
        this.householdService = householdService;
        this.householdConverter = householdConverter;
    }

    @NonNull
    @Override
    public List<HouseholdData> getHouseholds() {
        return householdService.getAllHouseholds().stream()
                .map(householdConverter::from)
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    public Optional<HouseholdData> getById(long id) {
        return householdService.getById(id).map(householdConverter::from);
    }

    @Override
    public void addHousehold(@NonNull HouseholdData household) {
        householdService.addHousehold(householdConverter.to(household));

        // example of another potential call: countyHealthAuthorityService.informAboutHelpBeingGivenToHousehold(household.getId)
        // example of another potential call: redCrossService.sendForReport(household.getId())
    }

    @Override
    public void updateHousehold(@NonNull HouseholdData household) {
        householdService.updateHousehold(householdConverter.to(household));
    }

    @Override
    public void deleteHousehold(long id) {
        householdService.deleteHousehold(id);
    }
}
