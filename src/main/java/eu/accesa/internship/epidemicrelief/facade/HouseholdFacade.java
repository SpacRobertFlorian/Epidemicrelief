package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface HouseholdFacade {

    @NonNull
    List<HouseholdData> getHouseholds();

    @NonNull
    Optional<HouseholdData> getById(long id);

    void addHousehold(@NonNull HouseholdData household);

    void updateHousehold(@NonNull HouseholdData household);

    void deleteHousehold(long id);
}
