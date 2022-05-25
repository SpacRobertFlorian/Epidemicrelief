package eu.accesa.internship.epidemicrelief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:epidemicrelief-spring.xml")
public class Launcher {

    //TODO put the time threshold in database(a new column) and take it whenever I need it and change it from front
    public static void main(String[] args) {

        SpringApplication.run(Launcher.class, args);
    }
}