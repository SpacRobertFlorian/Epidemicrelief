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
            Household costel = new Household("Costel", 4L, "0712345678", 2L, 2L, 2L,"costi_fortosul@yahoo.com","Str:Castori Stirbi nr:4");
            Household ana = new Household("Ana", 5L, "0721224567", 2L, 3L, 2L,"ana.catina@gmail.com","Str:Lalelelor nr:9");
            Household crina = new Household("Crina", 3L, "0798063326", 2L, 1L, 2L,"crina_floarea@yahoo.com","Str:Santierului nr:1223");
            householdRepository.saveAll(List.of(costel, ana, crina));
        };
    }
}
