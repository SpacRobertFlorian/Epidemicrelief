package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles {@link Necessity} entities.
 */
@Repository
public interface NecessityRepository extends CrudRepository<Necessity, Long> {
    /**
     * Find all persons categories currently in the database
     * Overridden to return a {@link List} instead of {@link Iterable}.
     *
     * @param personCategory a {@link PersonCategory} to search by
     * @return a list of necessity
     */
    @NonNull
    List<Necessity> findAllByPersonCategory(@NonNull PersonCategory personCategory);
}
