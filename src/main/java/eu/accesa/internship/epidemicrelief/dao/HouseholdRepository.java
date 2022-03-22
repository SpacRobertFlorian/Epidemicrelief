package eu.accesa.internship.epidemicrelief.dao;

import eu.accesa.internship.epidemicrelief.model.Household;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseholdRepository extends CrudRepository<Household, Long> {

    @NonNull
    List<Household> findAll();

}
