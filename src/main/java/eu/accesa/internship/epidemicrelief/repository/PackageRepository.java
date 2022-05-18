package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Package;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRepository extends CrudRepository<Package, Long> {
    @Override
    @NotNull
    List<Package> findAll();

}
