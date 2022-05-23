package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NecessityRepository extends CrudRepository<Necessity, Long> {
    List<Necessity> findAllByPersonCategory(PersonCategory personCategory);
}
