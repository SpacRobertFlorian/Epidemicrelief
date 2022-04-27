package eu.accesa.internship.epidemicrelief;

import eu.accesa.internship.epidemicrelief.data.ProductCategory;
import eu.accesa.internship.epidemicrelief.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication
@ImportResource("classpath:epidemicrelief-spring.xml")
public class Launcher {
    //TODO webapp JSP
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}