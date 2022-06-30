package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.rest.consuming.RESTClient;
import eu.accesa.internship.epidemicrelief.service.JobService;
import eu.accesa.internship.epidemicrelief.soap.consuming.Client;
import eu.accesa.internship.wsdl.GetProductRequest;
import eu.accesa.internship.wsdl.GetProductResponse;
import eu.accesa.internship.wsdl.ListName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    @Value("${batch.size}")
    private int bachSize;

    private final Client client;
    private final RESTClient restClient = new RESTClient();
    private final ProductRepository productRepository;
    private int offset = 0;

    @Autowired
    public DefaultJobService(Client client, ProductRepository productRepository) {
        this.client = client;
        this.productRepository = productRepository;
    }

//    @Override
//    @Scheduled(fixedRateString = "PT10S", zone = "Europe/Romania")
//    public void restUpdateProduct() {
//
//        List<Product> products = productRepository.findAll();
//        for (Product p : products) {
//            ResponseEntity<Product> response = restClient.getProduct(p.getName());
//            if (response.getStatusCode().is2xxSuccessful()) {
//                p.setStock(Objects.requireNonNull(response.getBody()).getStock());
//            }
//        }
//        productRepository.saveAll(products);
//    }

    @Override
    //@Scheduled(cron = "0 0 * * *", zone = "Europe/Romania")
    @Scheduled(fixedRateString = "PT5S", zone = "Europe/Romania")
    public void soapUpdateProduct() {
        //TODO sql query with limit, offset and order by
        //folosesc count pentru a afla finalul
        //List<Product> products = productRepository.findAll();
        int numberOfRows = productRepository.countAll();

        List<Product> products = getNProducts(bachSize, offset);
        offset += bachSize;
        ListName listName = createRequest(products);
        GetProductResponse response = executeRequest(listName);
        update(response);
    }

    private List<Product> getNProducts(int bachSize, int offset) {
        return productRepository.getNProducts(bachSize, offset);
    }

    private void update(GetProductResponse response) {

        List<eu.accesa.internship.wsdl.Product> products = response.getList().getProduct();
        for (eu.accesa.internship.wsdl.Product p : products) {
            Optional<Product> product = productRepository.findByUuid(p.getUuid());
            if (product.isPresent()) {
                product.get().setStock(p.getStock());
                productRepository.save(product.get());
            }
        }
    }

    private GetProductResponse executeRequest(ListName request) {
        GetProductResponse response;
        response = client.getProducts(request);
        return response;
    }

    private ListName createRequest(List<Product> products) {
        ListName listName = new ListName();
        for (Product p : products) {
            listName.getName().add(p.getName());
        }
        return listName;
    }
}
