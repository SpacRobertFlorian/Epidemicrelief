package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.service.HouseholdService;
import org.springframework.lang.NonNull;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultHouseholdService implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public DefaultHouseholdService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @NonNull
    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @NonNull
    @Override
    public Optional<Household> getById(long id) {
        return householdRepository.findById(id);
    }

    @Override
    public void addHousehold(@NonNull Household household) {
        Objects.requireNonNull(household);

        if (household.getId() != null) {
            throw new IllegalArgumentException("Entity which is not yet persisted expected to have null id");
        }

        householdRepository.save(household);
    }

    @Override
    public void updateHousehold(@NonNull Household household) {
        Objects.requireNonNull(household);

        Optional<Household> householdOptional = householdRepository.findById(household.getId());
        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find household to update; id: " + household.getId());
        }

        householdRepository.save(household);
    }

    @Override
    public void deleteHousehold(long id) {
        Optional<Household> household = householdRepository.findById(id);

        if (household.isPresent()) {
            householdRepository.delete(household.get());
        } else {
            throw new EntityNotFoundException("Unable to find household to delete; id: " + id);
        }
    }
}
