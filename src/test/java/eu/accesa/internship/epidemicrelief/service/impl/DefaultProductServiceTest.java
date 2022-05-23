package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultProductServiceTest {

    private static final long PRODUCT_ID = 1L;
    private static final ProductCategory PRODUCT_CATEGORY = ProductCategory.DRINKS;
    @Mock
    private ProductRepository productRepository;
    private DefaultProductService productService;

    @Mock
    private Product product;

    @Before
    public void setUp() {
        initMocks(this);
        productService = new DefaultProductService(productRepository);
    }

    @Test
    public void getAllProducts() {

        List<Product> productList = Mockito.spy(new ArrayList<>());
        productList.add(product);
        productList.add(product);
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> returnedProductList = productService.getAllProducts();

        verify(productRepository).findAll();
        assertSame(returnedProductList, productList);
    }

    @Test
    public void givenNoneExistingIdProduct_whenGetById_expectEmptyResult() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

        Optional<Product> returnedProduct = productService.getById(PRODUCT_ID);

        assertTrue(returnedProduct.isEmpty());
    }

    @Test
    public void givenExistingIdProduct_whenGetById_expectProduct() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        Optional<Product> returnedProduct = productService.getById(PRODUCT_ID);

        assertTrue(returnedProduct.isPresent());
        assertSame(returnedProduct.get(), product);
    }

    @Test
    public void givenValidProduct_whenAddProduct_saveProduct() {
        Product product = new Product();

        productService.addProduct(product);

        verify(productRepository).save(product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidProduct_whenAddProduct_expectThrowIllegalArgumentException() {
        product.setId(PRODUCT_ID);

        productService.addProduct(product);
    }

    @Test
    public void givenValidProduct_whenEditProduct_updateProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        productService.updateProduct(product);
        verify(productRepository).findById(PRODUCT_ID);
        verify(productRepository).save(product);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidProduct_whenEditProduct_updateProduct() {

        productService.updateProduct(product);

        verify(productRepository).save(product);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidIdProduct_whenDeleteProduct_deleteProduct() {

        productService.deleteProduct(PRODUCT_ID);

        verify(productRepository).delete(product);

    }

    @Test
    public void givenValidIdProduct_whenDeleteProduct_deleteProduct() {
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        productService.deleteProduct(PRODUCT_ID);

        verify(productRepository).findById(PRODUCT_ID);
        verify(productRepository).delete(product);
    }

    @Test
    public void givenValidCategory_whenGetByCategory_getListOfProducts() {
        List<Product> productList = new ArrayList<>();
        List<Product> spyList = spy(productList);

        Product product1 = new Product();
        Product spyProduct = spy(product1);

        spyProduct.setProductCategory(PRODUCT_CATEGORY);
        spyList.add(spyProduct);

        when(productRepository.findAllByProductCategory(PRODUCT_CATEGORY)).thenReturn(spyList);

        productService.getByCategory(PRODUCT_CATEGORY);

        verify(productRepository).findAllByProductCategory(PRODUCT_CATEGORY);
    }
}