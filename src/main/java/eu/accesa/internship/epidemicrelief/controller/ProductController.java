package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;
    private static final String PRODUCT_LIST_JSP = "product/productList";
    private static final String PRODUCTS_URL = "redirect:/products";
    private static final String UPDATE_PRODUCT_URL = "product/updateProduct";
    private static final String ADD_PRODUCT_URL = "product/addProduct";
    @Value("${minim.stock.threshold}")
    private int threshold;
    @Value("${internationalization.locale}")
    private String locale;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("locale", locale);
        model.addAttribute("products", productFacade.getProducts());
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        model.addAttribute("threshold", threshold);
        return PRODUCT_LIST_JSP;
    }

    @GetMapping("/category/{category}")
    public String getAllProductsFilteredByCategory(Model model, @PathVariable("category") String category) {
        try {
            ProductCategory x = ProductCategory.valueOf(category.toUpperCase());
            model.addAttribute("products", productFacade.getByCategory(x));
            model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
            model.addAttribute("threshold", threshold);
            return PRODUCT_LIST_JSP;

        } catch (IllegalArgumentException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @PostMapping
    public String getProductsFilteredByCategory(ProductCategory productCategory, Model model) {
        if (productCategory == null) {
            return PRODUCTS_URL;
        }
        return PRODUCTS_URL + "/category/" + productCategory;
    }

    @GetMapping("/new")
    public String getNewProductForm(Model model) {
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));

        return ADD_PRODUCT_URL;
    }

    @PostMapping(value = "/save")
    public String addProduct(@Valid ProductData productData, BindingResult result, Model model) {
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        if (result.hasErrors() || productData.getStock() < 0) {
            model.addAttribute("bindingResultMsg", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            //return PRODUCTS_URL + "/new";
            return ADD_PRODUCT_URL;
        }
        productFacade.addProduct(productData);
        return PRODUCTS_URL;
    }

    @GetMapping("/edit/{id}")
    public String getUpdateProductForm(@PathVariable("id") long id, Model model) {
        Optional<ProductData> productData = this.productFacade.getById(id);
        if (productData.isEmpty()) {

            throw new CustomException("No product exists for id " + id);
        }
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        model.addAttribute("product", productData.get());
        return UPDATE_PRODUCT_URL;
    }

    @PostMapping("/update")
    public String updateProduct(@Valid ProductData productData, BindingResult result, Model model) {
        if (result.hasErrors() || productData.getStock() < 0) {
            model.addAttribute("bindingResultMsg", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return PRODUCTS_URL + "/edit/" + productData.getId();
        }
        productFacade.updateProduct(productData);
        return PRODUCTS_URL;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productFacade.deleteProduct(id);
        return PRODUCTS_URL;
    }

}
