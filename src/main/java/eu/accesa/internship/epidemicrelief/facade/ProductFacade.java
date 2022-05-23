package eu.accesa.internship.epidemicrelief.facade;

import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Facade responsible for operation on {@link Product}
 */
public interface ProductFacade {
    /**
     * Get a list of ProductData
     *
     * @return list of all products
     */
    @NotNull
    List<ProductData> getProducts();

    /**
     * Get a ProductData by id
     *
     * @param id id of the product to be search by
     * @return an Optional containing the matching products if it exists; empty Optional otherwise
     */
    @NotNull
    Optional<ProductData> getById(long id);

    /**
     * Adds the given product
     *
     * @param productData the product to be added
     */
    void addProduct(@NotNull ProductData productData);

    /**
     * Updates the given product
     *
     * @param productData the product to be updated
     */
    void updateProduct(@NotNull ProductData productData);

    /**
     * Delete the given product
     *
     * @param id id of the product to be deleted
     */
    void deleteProduct(long id);

    /**
     * Get a list of products data by a category
     * @param productCategory a {@link ProductCategory} to search by
     * @return a list of products data
     */
    @NotNull
    List<ProductData> getByCategory(ProductCategory productCategory);
}
