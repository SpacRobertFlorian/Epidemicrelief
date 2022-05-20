package eu.accesa.internship.epidemicrelief.config;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HouseholdConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(HouseholdRepository householdRepository) {
        return args -> {
            Household costel = new Household("Costel", 4, "07222222", 2, 2, 2);
            Household ana = new Household("Ana", 5, "0733333", 2, 3, 2);
            Household crina = new Household("Crina", 3, "0754112112", 2, 1, 2);
            householdRepository.saveAll(List.of(costel, ana, crina));
        };
    }
}
