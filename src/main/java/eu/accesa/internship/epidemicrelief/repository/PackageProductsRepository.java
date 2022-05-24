package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProductId;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Repository that handles {@link PackageProducts} entities.
 */
@Repository
public interface PackageProductsRepository extends CrudRepository<PackageProducts, PackageProductId> {

    /**
     * Get all Package products search by a package
     * Overridden to return a {@link List} instead of {@link Iterable}.
     *
     * @param packageId a {@link Package} to search by
     * @return a list of package products
     */
    @NonNull
    List<PackageProducts> getAllByPack(Package packageId);

    @NonNull
    List<PackageProducts> findById_PackageId(Long id_packageId);

    /**
     * Deletes a package product by a package id
     *
     * @param pack_id an id of a package to delete by
     */
    @Modifying
    @Query("delete from PackageProducts p where p.pack.id=:pack_id")
    void delete(@Param("pack_id") Long pack_id);
}
