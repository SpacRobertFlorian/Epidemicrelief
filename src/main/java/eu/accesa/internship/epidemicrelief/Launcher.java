package eu.accesa.internship.epidemicrelief;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

@SpringBootApplication
@ImportResource("classpath:epidemicrelief-spring.xml")
public class Launcher {

    public static void main(String[] args) {

        SpringApplication.run(Launcher.class, args);
    }
}