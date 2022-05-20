package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProductId;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PackageProductsRepository extends CrudRepository<PackageProducts, PackageProductId> {

    @NotNull
    List<PackageProducts> getAllByPack(Package packageId);
}
