package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.service.ProductService;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NotNull
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @NotNull
    @Override
    public Optional<Product> getById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void addProduct(@NotNull Product product) {
        Objects.requireNonNull(product);
        if (product.getId() != null) {
            throw new IllegalArgumentException("Entity which is not yet persisted expected to have null id");
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(@NotNull Product product) {
        Objects.requireNonNull(product);

        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find household to update; id: " + product.getId());
        }

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("Unable to find household to update; id: " + id);
        } else {
            productRepository.delete(product.get());
        }
    }
}
