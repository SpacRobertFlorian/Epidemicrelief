package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Product;

import javax.validation.constraints.NotNull;

public class ProductConverter {

    @NotNull
    public ProductData from(@NotNull Product source) {
        ProductData target = new ProductData();

        target.setId(source.getId());
        target.setProductCategory(source.getProductCategory());
        target.setName(source.getName());
        target.setStock(source.getStock());

        return target;
    }

    @NotNull
    public Product to(@NotNull ProductData source) {
        Product target = new Product();

        target.setId(source.getId());
        target.setProductCategory(source.getProductCategory());
        target.setName(source.getName());
        target.setStock(source.getStock());

        return target;
    }
}
