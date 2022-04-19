package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Facade responsible for operations performed on {@link HouseholdData}.
 */
public interface HouseholdFacade {

    /**
     * @return list of all households
     */
    @NonNull
    List<HouseholdData> getHouseholds();

    /**
     * @return an Optional containing the matching household if it exists; empty optional otherwise
     */
    @NonNull
    Optional<HouseholdData> getById(long id);

    /**
     * Adds the given household.
     *
     * @param household the household to be added
     */
    void addHousehold(@NonNull HouseholdData household);

    /**
     * Updates the given household.
     *
     * @param household the household to be updated
     */
    void updateHousehold(@NonNull HouseholdData household);

    /**
     * Removes the household with the given id.
     *
     * @param id id of the household to be removed
     */
    void deleteHousehold(long id);
}
