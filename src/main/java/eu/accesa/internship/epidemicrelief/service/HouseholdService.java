package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.model.Household;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Contains business logic related to households.
 */
public interface HouseholdService {

    /**
     * @return list of all households
     */
    @NonNull
    List<Household> getAllHouseholds();

    /**
     * @return an Optional containing the matching household if it exists; empty optional otherwise
     */
    @NonNull
    Optional<Household> getById(long id);

    /**
     * Adds the given household.
     *
     * @param household the household to be added
     */
    void addHousehold(@NonNull Household household);

    /**
     * Updates the given household.
     *
     * @param household the household to be updated
     */
    void updateHousehold(@NonNull Household household);

    /**
     * Removes the household with the given id.
     *
     * @param id id of the household to be removed
     */
    void deleteHousehold(long id);
}
