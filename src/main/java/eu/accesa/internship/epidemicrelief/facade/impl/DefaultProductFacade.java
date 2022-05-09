package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.ProductConverter;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import eu.accesa.internship.epidemicrelief.service.ProductService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultProductFacade implements ProductFacade {

    private final ProductService productService;
    private final ProductConverter productConverter;

    public DefaultProductFacade(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @NotNull
    @Override
    public List<ProductData> getProducts() {
        return this.productService.getAllProducts()
                .stream()
                .map(productConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public Optional<ProductData> getById(long id) {
        return this.productService.getById(id).map(productConverter::from);
    }

    @Override
    public void addProduct(@NotNull ProductData productData) {
        productService.addProduct(productConverter.to(productData));
    }

    @Override
    public void updateProduct(@NotNull ProductData productData) {
        productService.updateProduct(productConverter.to(productData));
    }

    @Override
    public void deleteProduct(long id) {
        this.productService.deleteProduct(id);
    }

    @Override
    public List<ProductData> getByCategory(ProductCategory productCategory) {
        return this.productService.getByCategory(productCategory)
                .stream()
                .map(productConverter::from)
                .collect(Collectors.toList());
    }
}
