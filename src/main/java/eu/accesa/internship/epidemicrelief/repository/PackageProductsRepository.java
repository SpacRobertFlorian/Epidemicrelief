package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProductId;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface PackageProductsRepository extends CrudRepository<PackageProducts, PackageProductId> {

    @NotNull
    List<PackageProducts> getAllByPack(Package packageId);

    @NotNull
    List<PackageProducts> findById_PackageId(Long id_packageId);

    @Modifying
    @Query("delete from PackageProducts p where p.pack.id=:pack_id")
    void delete(@Param("pack_id") Long pack_id);
}
