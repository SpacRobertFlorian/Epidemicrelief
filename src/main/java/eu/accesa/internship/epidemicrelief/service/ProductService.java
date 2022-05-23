package eu.accesa.internship.epidemicrelief.service;

import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Contains business logic related to products
 */
public interface ProductService {

    /**
     * Get a list of Products
     *
     * @return list of all products
     */
    List<Product> getAllProducts();

    /**
     * Get a Product by id
     *
     * @param id id of the product to be search by
     * @return an {@link Optional} containing the matching products if it exists; {@link  Optional#empty}
     */
    @NotNull
    Optional<Product> getById(long id);

    /**
     * Adds the given Product
     *
     * @param product the product to be added
     */
    void addProduct(@NotNull Product product);

    /**
     * Update the given product
     *
     * @param product the product to be updated
     */
    void updateProduct(@NotNull Product product);

    /**
     * Delete the product with the given id
     *
     * @param id id of the product to be deleted
     */
    void deleteProduct(long id);

    /**
     * Get a list of Products filtered by category
     * @param productCategory is the category to be filtered
     * @return list of products
     */
    List<Product> getByCategory(ProductCategory productCategory);
}
