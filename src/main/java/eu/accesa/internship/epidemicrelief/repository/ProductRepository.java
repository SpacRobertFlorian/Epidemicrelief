package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;
import org.springframework.data.repository.CrudRepository;
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
    @NotNull
    @Override
    List<Product> findAll();

    @NotNull
    List<Product> findAllByProductCategory(ProductCategory productCategory);

    @NotNull
    Optional<Product> findByUuid(String uuid);
    Optional<Product> findProductByUuid(String uuid);


}
