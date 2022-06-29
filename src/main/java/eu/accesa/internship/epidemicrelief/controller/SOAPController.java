package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.soap.consuming.Client;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import eu.accesa.internship.wsdl.GetProductResponse;
import eu.accesa.internship.wsdl.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soap")
public class SOAPController {
    private final Client client;

    public SOAPController(Client client) {
        this.client = client;
    }

    @GetMapping("/{name}")
    public eu.accesa.internship.epidemicrelief.model.Product index(@PathVariable String name) {
        GetProductResponse request = client.getProduct(name);
        Product product = request.getProduct();
        eu.accesa.internship.epidemicrelief.model.Product p = new eu.accesa.internship.epidemicrelief.model.Product();
        p.setName(product.getName());
        p.setStock(product.getStock());
        p.setUuid(product.getUuid());
        p.setProductCategory(ProductCategory.valueOf(product.getProductCategory()));

        return p;
    }
}
