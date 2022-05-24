package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Package;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends CrudRepository<Package, Long> {
    /**
     * Finds all packages currently in the database.
     * Overridden list of a {@link List} instead of {@link  Iterable}.
     *
     * @return list of packages in the database
     */
    @Override
    @NonNull
    List<Package> findAll();

}
