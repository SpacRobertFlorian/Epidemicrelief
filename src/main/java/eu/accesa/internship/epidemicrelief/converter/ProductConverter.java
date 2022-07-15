package eu.accesa.internship.epidemicrelief.converter;

import com.mysql.cj.log.Log;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.soap.consuming.SOAPClient;
import eu.accesa.internship.epidemicrelief.utils.Internationalization;
import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.util.Locale;

/**
 * Converter responsible for converting {@link ProductData} to {@link Product}
 * and
 * {@link Product} to {@link ProductData}
 */
public class ProductConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SOAPClient.class);


    /**
     * Converts Product to ProductData
     *
     * @param source the {@link Product} to be converted
     * @return an ProductData containing data from Product
     */
    private final Internationalization internationalization;

    public ProductConverter(Internationalization internationalization) {
        this.internationalization = internationalization;
    }

    @NotNull
    public ProductData from(@NotNull Product source) {
        ProductData target = new ProductData();

        target.setId(source.getId());
        target.setProductCategory(internationalization.translateStaticWords(source.getProductCategory().getCategory()));
        try {
            target.setName(internationalization.translateDynamicWords(source.getName()));
            target.setStock(source.getStock());
            target.setUuid(source.getUuid());
            target.setPrice(internationalization.calculateCurrency(source.getPrice(), source.getCurrency()));
            target.setCurrency(Currency.valueOf(internationalization.getCurrency(internationalization.getCountry()).toUpperCase()));
        } catch (CustomException e) {
            LOGGER.warn(e.getMessage());
        }

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
        target.setCurrency(source.getCurrency());

        return target;
    }

    public void setLocale(String lang, String country) {
        Locale locale = new Locale(lang, country);
        this.internationalization.setLocale(locale);
    }
}
