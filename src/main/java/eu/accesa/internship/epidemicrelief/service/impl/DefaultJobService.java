package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.rest.consuming.RESTClient;
import eu.accesa.internship.epidemicrelief.service.JobService;
import eu.accesa.internship.epidemicrelief.soap.consuming.SOAPClient;
import eu.accesa.internship.wsdl.GetProductResponse;
import eu.accesa.internship.wsdl.ListName;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    @Value("${batch.size}")
    private int bachSize;

    private final SOAPClient client;
    private final RESTClient restClient = new RESTClient();
    private final ProductRepository productRepository;
    private int offset = 0;

    @Autowired
    public DefaultJobService(SOAPClient client, ProductRepository productRepository) {
        this.client = client;
        this.productRepository = productRepository;
    }

    //    @Override
//    @Scheduled(fixedRateString = "PT10S", zone = "Europe/Romania")
//    public void restUpdateProduct() {
//        List<Product> products = productRepository.findAll();
//        for (Product p : products) {
//            ResponseEntity<Product> response = restClient.getProduct(p.getName());
//            if (response.getStatusCode().is2xxSuccessful()) {
//                p.setStock(Objects.requireNonNull(response.getBody()).getStock());
//            }
//        }
//        productRepository.saveAll(products);
//    }

    //TODO Nu merge, da eroare 404 cand apelez restClient
    //localhost:8082/products?name=Vitamin Juice nu cred ca merge asa
    //imi face asa products%5BCanned%20Vegetables,%20Chocolate,%20Meat%5D"
    @Override
    @Scheduled(fixedRateString = "PT10S", zone = "Europe/Romania")
    public void restUpdateProduct() {
        List<Product> products = getNProducts(bachSize, offset);
        offset += bachSize;
        List<String> request = createRestRequest(products);
        ResponseEntity<List<Product>> response = executeRequest(request);
        update(response);
    }

    private List<String> createRestRequest(List<Product> products) {
        List<String> productNames = new ArrayList<>();
        for (Product p : products) {
            productNames.add(p.getName());
        }
        return productNames;
    }

    private ResponseEntity<List<Product>> executeRequest(List<String> request) {
        return restClient.getProducts(request);
    }

    private void update(ResponseEntity<List<Product>> response) {
        List<Product> products = response.getBody();
        for (Product p : products) {
            Optional<Product> getProduct = productRepository.findProductByUuid(p.getUuid());
            if (getProduct.isPresent()) {
                getProduct.get().setStock(p.getStock());
                productRepository.save(getProduct.get());
            }
        }
    }

//    @Override
//    //@Scheduled(cron = "0 0 * * *", zone = "Europe/Romania")
//    @Scheduled(fixedRateString = "PT5S", zone = "Europe/Romania")
//    public void soapUpdateProduct() {
//
//        List<Product> products = getNProducts(bachSize, offset);
//        offset += bachSize;
//        ListName listName = createRequest(products);
//        GetProductResponse response = executeRequest(listName);
//        update(response);
//    }
//
//
//    private void update(GetProductResponse response) {
//
//        List<eu.accesa.internship.wsdl.Product> products = response.getList().getProduct();
//        for (eu.accesa.internship.wsdl.Product p : products) {
//            Optional<Product> product = productRepository.findByUuid(p.getUuid());
//            if (product.isPresent()) {
//                product.get().setStock(p.getStock());
//                productRepository.save(product.get());
//            }
//        }
//    }
//
//    private GetProductResponse executeRequest(ListName request) {
//        GetProductResponse response;
//        response = client.getProducts(request);
//        return response;
//    }
//
//    private ListName createRequest(List<Product> products) {
//        ListName listName = new ListName();
//        for (Product p : products) {
//            listName.getName().add(p.getName());
//        }
//        return listName;
//    }

    private List<Product> getNProducts(int bachSize, int offset) {
        return productRepository.getNProducts(bachSize, offset);
    }
}
