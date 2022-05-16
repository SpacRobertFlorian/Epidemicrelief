package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Household;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Repository that handles {@link Household} entities.
 */
@Repository
public interface HouseholdRepository extends CrudRepository<Household, Long> {

    /**
     * Finds all households currently in the database.
     * <br/>
     * Overridden to return a {@link List} instead of {@link Iterable}.
     *
     * @return list of households in the database
     */
    @NonNull
    @Override
    List<Household> findAll();
}
