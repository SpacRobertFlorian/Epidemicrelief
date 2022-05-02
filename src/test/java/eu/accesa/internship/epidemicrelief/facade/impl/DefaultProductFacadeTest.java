package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.ProductConverter;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultProductFacadeTest {

    private static final long PRODUCT_ID = 1L;
    private DefaultProductFacade productFacade;
    @Mock
    private ProductService productService;
    @Mock
    private ProductConverter productConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productFacade = new DefaultProductFacade(productService, productConverter);
    }

    @Test
    public void giveNonExistingProduct_whenGetById_expectEmptyResult() {
        //TODO understand this fking when
        when(productService.getById(PRODUCT_ID)).thenReturn(Optional.empty());

        Optional<ProductData> returnedProduct = productFacade.getById(PRODUCT_ID);

        assertTrue(returnedProduct.isEmpty());
    }

    @Test
    public void giveExistingProduct_whenGetById_expectedProductConvertedToData() {
        Product product = new Product();
        ProductData productData = new ProductData();
        when(productService.getById(PRODUCT_ID)).thenReturn(Optional.of(product));
        when(productConverter.from(product)).thenReturn(productData);

        Optional<ProductData> returnedProduct = productFacade.getById(PRODUCT_ID);

        assertTrue(returnedProduct.isPresent());
        assertSame(productData, returnedProduct.get());
    }

    @Test
    public void givenValidProduct_whenAddProduct_saveProduct() {
        Product product = new Product();
        ProductData productData = new ProductData();
        when(productConverter.to(productData)).thenReturn(product);

        productFacade.addProduct(productData);

        verify(productService).addProduct(product);
        verify(productConverter).
    }

}