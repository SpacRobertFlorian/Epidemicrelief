package eu.accesa.internship.epidemicrelief.config;

import eu.accesa.internship.epidemicrelief.model.DeliveryDateThreshold;
import eu.accesa.internship.epidemicrelief.repository.DeliveryDateThresholdRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryDateThresholdConfiguration {
    @Bean
    CommandLineRunner commandLineRunner2(DeliveryDateThresholdRepository repository) {
        return args -> {
            DeliveryDateThreshold threshold = new DeliveryDateThreshold(-1);
            repository.save(threshold);
        };
    }
}
