package eu.accesa.internship.epidemicrelief.controller;

import eu.accesa.internship.epidemicrelief.rest.consuming.RESTClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RESTController {

    @GetMapping("/{name}")
    public ResponseEntity<String> getProduct(@PathVariable String name) {
        RESTClient client = new RESTClient();
        return client.getProduct(name);
    }
}
