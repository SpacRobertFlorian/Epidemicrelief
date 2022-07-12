package eu.accesa.internship.epidemicrelief.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validator;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductTest {
    private Validator validator;
    private final String BLANK_STRING = "";
    private final String PROPERTY_NAME_STOCK = "stock";
    private final String PROPERTY_NAME_NAME = "name";

    @Before
    public void setUp() {
        initMocks(this);
        validator = buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void givenInvalidStockHousehold_whenSaveHousehold_expectException() {
        Product product = new Product();
        product.setStock(-1L);
        assertEquals(validator.validateProperty(product, PROPERTY_NAME_STOCK).size(), 1);
    }

    @Test
    public void givenValidStockHousehold_whenSaveHousehold_expectException() {
        Product product = new Product();
        product.setStock(1L);
        assertEquals(validator.validateProperty(product, PROPERTY_NAME_STOCK).size(), 0);
    }

    @Test
    public void givenInvalidNameHousehold_whenSaveHousehold_expectException() {
        Product product = new Product();
        product.setName(BLANK_STRING);
        assertEquals(validator.validateProperty(product, PROPERTY_NAME_NAME).size(), 2);
    }

    @Test
    public void givenInvalidNameSizeHousehold_whenSaveHousehold_expectException() {
        Product product = new Product();
        product.setName("a");
        assertEquals(validator.validateProperty(product, PROPERTY_NAME_NAME).size(), 1);
    }

    @Test
    public void givenValidNameHousehold_whenSaveHousehold_expectException() {
        Product product = new Product();
        product.setName("oranges");
        assertEquals(validator.validateProperty(product, PROPERTY_NAME_NAME).size(), 0);
    }
}
