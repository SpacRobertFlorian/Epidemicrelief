package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.ProductConverter;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultProductFacadeTest {

    private static final long PRODUCT_ID = 1L;
    private static final ProductCategory PRODUCT_CATEGORY = ProductCategory.DRINKS;
    private DefaultProductFacade productFacade;
    @Mock
    private ProductService productService;
    @Mock
    private ProductConverter productConverter;

    @Before
    public void setUp() {
        initMocks(this);
        productFacade = new DefaultProductFacade(productService, productConverter);
    }

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
    public void givenNothing_whenGetAllProducts_thenAllProductsConvertedToData() {
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

    @Test
    public void givenProductCategory_whenGetByCategory_thenGetListOfProducts() {
        List<Product> productList = Mockito.spy(new ArrayList<>());
        Product product = new Product();
        ProductData productData = new ProductData();

        product.setProductCategory(PRODUCT_CATEGORY);
        productList.add(product);

        when(productService.getByCategory(PRODUCT_CATEGORY)).thenReturn(productList);
        when(productConverter.from(product)).thenReturn(productData);

        List<ProductData> byCategory = productFacade.getByCategory(PRODUCT_CATEGORY);

        assertEquals(1, byCategory.size());
        assertEquals(productData, byCategory.get(0));

        verify(productService).getByCategory(PRODUCT_CATEGORY);
        verify(productConverter).from(product);
        verifyNoMoreInteractions(productConverter);
    }
}