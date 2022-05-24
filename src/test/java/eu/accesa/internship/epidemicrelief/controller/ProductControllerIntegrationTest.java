package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.Launcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Launcher.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    private static final String GET_PRODUCT_URL = "/products";
    private static final String SAVE_PRODUCT_URL = "/products/save";

    private static final String MIN_STOCK_THRESHOLD = "threshold";
    private static final String PRODUCTS_ATTRIBUTE = "products";
    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String CATEGORIES_ATTRIBUTE = "categories";
    private static final String GET_PRODUCTS_EXPECTED_VIEW_NAME = "product/productList";
    private static final String GET_PRODUCT_BY_ID_URL = "/products/edit/{id}";
    private static final String GET_EDIT_PRODUCT_EXPECTED_VIEW_NAME = "product/updateProduct";
    private static final String GET_DELETE_PRODUCT_BY_ID = "/products/delete/{id}";
    private static final String GET_NEW_PRODUCT = "/products/new";
    private static final String NAME_PARAMETER = "name";
    private static final String NAME_PARAMETER_VALUE = "Paracetamol";
    private static final String STOCK_PARAMETER = "stock";
    private static final String STOCK_PARAMETER_VALUE = "20";
    private static final String STOCK_INVALID_PARAMETER_VALUE = "-1";
    private static final String CATEGORY_PARAMETER = "product_category";
    private static final String CATEGORY_PARAMETER_VALUE = "product_category";


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenProductsURL_whenGetAllProducts_thenPerformGet() throws Exception {
        mockMvc.perform(get(GET_PRODUCT_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(PRODUCTS_ATTRIBUTE))
                .andExpect(model().attribute(PRODUCTS_ATTRIBUTE, hasSize(6)))
                .andExpect(view().name(GET_PRODUCTS_EXPECTED_VIEW_NAME));
    }

    @Test
    public void givenValidProductData_whenSaveProduct_thenPerformPost() throws Exception {
        mockMvc.perform(post(SAVE_PRODUCT_URL)
                        .params(fillParameters())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void givenInvalidProductData_whenSaveProduct_thenPerformRedirect() throws Exception {
        mockMvc.perform(post(SAVE_PRODUCT_URL)
                        .params(fillInvalidParameters())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + GET_NEW_PRODUCT));
    }


    @Test
    public void givenValidProductData_whenEditProduct_thenUpdateProduct() throws Exception {
        mockMvc.perform(get(GET_PRODUCT_BY_ID_URL, 1))
                .andExpect(model().attributeExists(PRODUCT_ATTRIBUTE))
                .andExpect(model().attributeExists(CATEGORIES_ATTRIBUTE))
                .andExpect(view().name(GET_EDIT_PRODUCT_EXPECTED_VIEW_NAME));
    }

    @Test
    public void givenValidId_whenDeleteProduct_thenDeleteProduct() throws Exception {
        mockMvc.perform(get(GET_DELETE_PRODUCT_BY_ID, "1"))
                .andExpect(view().name("redirect:" + GET_PRODUCT_URL));
    }

    private MultiValueMap<String, String> fillParameters() {
        MultiValueMap<String, String> params = new HttpHeaders();
        params.add(NAME_PARAMETER, NAME_PARAMETER_VALUE);
        params.add(STOCK_PARAMETER, STOCK_PARAMETER_VALUE);
        params.add(CATEGORY_PARAMETER, CATEGORY_PARAMETER_VALUE);

        return params;
    }

    private MultiValueMap<String, String> fillInvalidParameters() {
        MultiValueMap<String, String> params = new HttpHeaders();
        params.add(NAME_PARAMETER, null);
        params.add(STOCK_PARAMETER, STOCK_INVALID_PARAMETER_VALUE);
        params.add(CATEGORY_PARAMETER, null);

        return params;
    }
}