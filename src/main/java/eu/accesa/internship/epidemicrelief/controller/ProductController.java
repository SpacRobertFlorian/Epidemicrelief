package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.enums.ProductCategory;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productFacade.getProducts());
        return "product/productList";
    }

    @GetMapping("/new")
    public String getNewProductForm(Model model) {
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        return "product/addProduct";
    }

    //TODO Task 3
    // Validate stock to be greater then 1
    @PostMapping(value = "/save")
    public String addProduct(@Valid ProductData productData, BindingResult result, Model model) {
        if (result.hasErrors() || productData.getStock() < 0) {

            return "redirect:/products/new";
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String getUpdateProductForm(@PathVariable("id") long id, Model model) {
        Optional<ProductData> productData = this.productFacade.getById(id);
        if (productData.isEmpty()) {
            throw new IllegalArgumentException("No product exists for id " + id);
        }
        model.addAttribute("categories", Arrays.asList(ProductCategory.values()));
        model.addAttribute("product", productData.get());
        return "product/updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid ProductData productData, BindingResult result, Model model) {
        if (result.hasErrors() || productData.getStock() < 0) {
            return "redirect:/products/edit/" + productData.getId();
        }
        productFacade.updateProduct(productData);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productFacade.deleteProduct(id);
        return "redirect:/products";
    }

}
