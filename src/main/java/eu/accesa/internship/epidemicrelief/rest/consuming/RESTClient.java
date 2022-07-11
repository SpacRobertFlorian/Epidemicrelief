package eu.accesa.internship.epidemicrelief.rest.consuming;

import eu.accesa.internship.epidemicrelief.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RESTClient {

    public ResponseEntity<List<Product>> getProducts(List<String> name) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8082/products?name=";
        String products = createURL(name);
        return restTemplate.exchange(fooResourceUrl + products, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
    }

    private String createURL(List<String> name) {
        StringBuilder names = new StringBuilder();
        for (String s : name) {
            names.append(s);
            names.append(",");
        }
        if (names.length() > 0) {
            names.deleteCharAt(names.length() - 1);
        }
        return names.toString();
    }
}
