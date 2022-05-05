package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.ProductConverter;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductFacadeTest {

    private static final long PRODUCT_ID = 1L;
    @InjectMocks
    private DefaultProductFacade productFacade;
    @Mock
    private ProductService productService;
    @Mock
    private ProductConverter productConverter;


    @Test
    public void giveNonExistingProduct_whenGetById_expectEmptyResult() {
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

    //TODO ARGUMENTCAPTOR nu merge ceva da cu virgula
    @Test
    public void givenValidProduct_whenAddProduct_saveProduct() {
        Product product = new Product();
        ProductData productData = new ProductData();

        ArgumentCaptor<Product> capturedProduct = ArgumentCaptor.forClass(Product.class);

        when(productConverter.to(productData)).thenReturn(product);

        productFacade.addProduct(productData);

        verify(productService).addProduct(capturedProduct.capture());
        Product valueCapturedProduct = capturedProduct.getValue();
        assertEquals(product, valueCapturedProduct);

        verify(productService).addProduct(product);
        verify(productConverter).to(productData);
        verify(productConverter, never()).from(product);
    }

    @Test
    public void givenValidProduct_whenEditProduct_updateProduct() {
        Product product = new Product();
        ProductData productData = new ProductData();
        when(productConverter.to(productData)).thenReturn(product);

        productFacade.updateProduct(productData);

        verify(productService).updateProduct(product);
        verify(productConverter).to(productData);

        verifyNoMoreInteractions(productConverter);
    }

    @Test
    public void givenValidId_whenDeleteProduct_deleteProduct() {
        productFacade.deleteProduct(PRODUCT_ID);

        verify(productService, times(1)).deleteProduct(PRODUCT_ID);
        verifyNoMoreInteractions(productConverter);
    }

    @Test
    public void getAllProducts_thenAllProductsConvertedToData() {
        List<Product> productList = Mockito.spy(new ArrayList<>());
        Product product = new Product();

        productList.add(product);
        productList.add(product);
        productList.add(product);

        when(productService.getAllProducts()).thenReturn(productList);

        productFacade.getProducts();

        verify(productService).getAllProducts();
        verify(productConverter, times(3)).from(product);
        verifyNoMoreInteractions(productConverter);
    }

}