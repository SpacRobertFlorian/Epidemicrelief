package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.ProductConverter;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import eu.accesa.internship.epidemicrelief.service.ProductService;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultProductFacade implements ProductFacade {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @Value("${internationalization.country}")
    private String country;
    @Value("${internationalization.lang}")
    private String lang;

    public DefaultProductFacade(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @NotNull
    @Override
    public List<ProductData> getProducts() {
        productConverter.setLocale(lang, country);
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
        try {
            UUID uuid = UUID.randomUUID();
            productData.setUuid(uuid.toString());
            productService.addProduct(productConverter.to(productData));
        } catch (IllegalArgumentException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void updateProduct(@NotNull ProductData productData) {
        try {
            productService.updateProduct(productConverter.to(productData));
        } catch (EntityNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            this.productService.deleteProduct(id);
        } catch (EntityNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<ProductData> getByCategory(ProductCategory productCategory) {
        return this.productService.getByCategory(productCategory)
                .stream()
                .map(productConverter::from)
                .collect(Collectors.toList());
    }
}
