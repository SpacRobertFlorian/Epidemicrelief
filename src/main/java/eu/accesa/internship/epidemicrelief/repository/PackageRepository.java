package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface PackageRepository extends CrudRepository<Package, Long> {
    @NotNull
    @Override
    List<Package> findAll();

    Optional<Package> findTopByHouseholdOrderByHouseholdDesc(Household household);
}
