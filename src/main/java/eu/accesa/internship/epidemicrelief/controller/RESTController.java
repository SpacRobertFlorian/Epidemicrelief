package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.rest.consuming.RESTClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RESTController {

//    @GetMapping("/{name}")
//    public ResponseEntity<Product> getProduct(@PathVariable String name) {
//        RESTClient client = new RESTClient();
//        return client.getProducts(name);
//    }
//    @GetMapping("/{name}")
//    public String getProduct(@PathVariable String name) {
//        Optional<Product> product = restService.getProductByName(name);
//        return product.map(Product::toString).orElse(null);
//    }
}
