package eu.accesa.internship.epidemicrelief;

import eu.accesa.internship.epidemicrelief.entity.Child;
import eu.accesa.internship.epidemicrelief.entity.Family;
import eu.accesa.internship.epidemicrelief.entity.NonVegan;
import eu.accesa.internship.epidemicrelief.entity.Vegan;
import eu.accesa.internship.epidemicrelief.entity.visitor.ProductVisitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ImportResource("classpath:epidemicrelief-spring.xml")
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
//        ProductVisitor productVisitor = new ProductVisitor();
//        List<ProductNecessity> productNecessityList = new ArrayList<>();
//
//        Family family = new Family(5);
//        Vegan vegan = new Vegan(3);
//        NonVegan nonVegan = new NonVegan(2);
//        Child child = new Child(3);
//
//        productNecessityList.addAll(family.productNecessityList(productVisitor));
//        productNecessityList.addAll(vegan.productNecessityList(productVisitor));
//        productNecessityList.addAll(nonVegan.productNecessityList(productVisitor));
//        productNecessityList.addAll(child.productNecessityList(productVisitor));
//
//        productNecessityList.forEach(System.out::println);
    }
}