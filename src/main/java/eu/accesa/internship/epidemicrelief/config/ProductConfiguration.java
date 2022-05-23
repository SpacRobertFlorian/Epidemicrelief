package eu.accesa.internship.epidemicrelief.config;

import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.NecessityRepository;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;
import eu.accesa.internship.epidemicrelief.utils.enums.ProductCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfiguration {
    @Bean
    CommandLineRunner commandLineRunner1(ProductRepository productRepository, NecessityRepository repository) {
        return argv -> {
            Product water = new Product("4cafb0c1-1a55-46d4-baea-530ca06cda30", "Water", 10000L, ProductCategory.DRINKS);
            Product vitaminJuice = new Product("23f9cad8-c02a-4b90-a330-c727dfa7b94c", "Vitamin Juice", 10000L, ProductCategory.DRINKS);
            Product cannedVegetables = new Product("2fa9e768-be37-450c-b1d1-6c7bd0b1bced", "Canned Vegetables", 10000L, ProductCategory.FOOD);
            Product chocolate = new Product("a88fdac5-ab67-4bbb-b6f6-f874032244c8", "Chocolate", 10000L, ProductCategory.FOOD);
            Product meat = new Product("d5797a26-2a2a-4c47-8513-3f5ae51c0bae", "Meat", 10000L, ProductCategory.FOOD);


            Necessity child1 = new Necessity(PersonCategory.CHILD, 1L, vitaminJuice);
            Necessity child2 = new Necessity(PersonCategory.CHILD, 1L, chocolate);
            Necessity veg = new Necessity(PersonCategory.VEGAN, 10L, cannedVegetables);
            Necessity family = new Necessity(PersonCategory.FAMILY, 2L, water);
            Necessity nonVeg = new Necessity(PersonCategory.NON_VEGAN, 10L, meat);

            vitaminJuice.setNecessity(child1);
            chocolate.setNecessity(child2);
            cannedVegetables.setNecessity(veg);
            water.setNecessity(family);
            meat.setNecessity(nonVeg);

            repository.saveAll(List.of(child1, child2, veg, family, nonVeg));
            productRepository.saveAll(List.of(water, vitaminJuice, cannedVegetables, chocolate, meat));
        };
    }
}

