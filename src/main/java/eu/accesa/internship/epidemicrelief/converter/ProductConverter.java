package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Product;

import javax.validation.constraints.NotNull;

/**
 * Converter responsible for converting {@link ProductData} to {@link Product}
 * and
 * {@link Product} to {@link ProductData}
 */
public class ProductConverter {

    /**
     * Converts Product to ProductData
     *
     * @param source the {@link Product} to be converted
     * @return an ProductData containing data from Product
     */
    @NotNull
    public ProductData from(@NotNull Product source) {
        ProductData target = new ProductData();

        target.setId(source.getId());
        target.setProductCategory(source.getProductCategory());
        target.setName(source.getName());
        target.setStock(source.getStock());
        target.setUuid(source.getUuid());

        return target;
    }

    /**
     * Converts ProductData to Product
     *
     * @param source the {@link ProductData} to be converted
     * @return Product containing data from ProductData
     */
    @NotNull
    public Product to(@NotNull ProductData source) {
        Product target = new Product();

        target.setId(source.getId());
        target.setProductCategory(source.getProductCategory());
        target.setName(source.getName());
        target.setStock(source.getStock());
        target.setUuid(source.getUuid());

        return target;
    }
}
