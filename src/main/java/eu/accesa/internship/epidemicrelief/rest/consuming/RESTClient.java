package eu.accesa.internship.epidemicrelief.rest.consuming;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RESTClient {

    public ResponseEntity<String> getProduct(String name) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8082/products/";
        return restTemplate.getForEntity(fooResourceUrl + name, String.class);
    }
}
