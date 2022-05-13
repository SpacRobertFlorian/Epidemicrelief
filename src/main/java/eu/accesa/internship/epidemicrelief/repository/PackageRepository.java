package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Package;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackageRepository extends CrudRepository<Package, Long> {
    @Override
    List<Package> findAll();
}
