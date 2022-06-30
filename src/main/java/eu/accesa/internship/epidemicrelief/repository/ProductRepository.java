package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Repository that handles {@link Product} entities
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    /**
     * Finds all products currently in the database.
     * Overridden list of a {@link List} instead of {@link Iterable}.
     *
     * @return list of products in the database
     */
    @NonNull
    @Override
    List<Product> findAll();

    @NonNull
    List<Product> findAllByProductCategory(@NonNull ProductCategory productCategory);

    @NonNull
    Optional<Product> findByUuid(String uuid);

    @NonNull
    Optional<Product> findProductByUuid(String uuid);

    @NonNull
    @Query(
            value = "SELECT COUNT(*) FROM product",
            nativeQuery = true)
    Integer countAll();

    @NonNull
    @Query(value = "SELECT * FROM product ORDER BY name ASC LIMIT ?1 offset ?2",
            nativeQuery = true)
    List<Product> getNProducts(int numberOfRows, int offset);

}
