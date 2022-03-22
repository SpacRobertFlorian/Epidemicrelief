package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.model.Household;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Contains business logic related to households.
 */
public interface HouseholdService {

    @NonNull
    List<Household> getAllHouseholds();

    @NonNull
    Optional<Household> getById(long id);

    void addHousehold(@NonNull Household household);

    void updateHousehold(@NonNull Household household);

    void deleteHousehold(long id);
}
