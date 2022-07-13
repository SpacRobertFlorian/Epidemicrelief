package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.utils.Internationalization;
import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;

import javax.validation.constraints.NotNull;
import java.util.Locale;

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

    private final Internationalization internationalization = new Internationalization();

    @NotNull
    public ProductData from(@NotNull Product source) {
        ProductData target = new ProductData();

        target.setId(source.getId());
        target.setProductCategory(internationalization.translateWord(source.getProductCategory().getCategory()));
        target.setName(internationalization.translateWord(source.getName()));
        target.setStock(source.getStock());
        target.setUuid(source.getUuid());
        target.setPrice(source.getPrice());
        target.setPrice(internationalization.calculateCurrency(target.getPrice(), source.getCurrency()));
        target.setCurrency(Currency.valueOf(internationalization.getCurrency(internationalization.getCountry()).toUpperCase()));
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
        target.setProductCategory(ProductCategory.valueOf(source.getProductCategory()));
        target.setName(source.getName());
        target.setStock(source.getStock());
        target.setUuid(source.getUuid());
        target.setPrice(source.getPrice());

        return target;
    }
}
