package eu.accesa.internship.epidemicrelief.utils;

import org.springframework.beans.factory.annotation.Value;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
    //TODO nu merge nu stiu dc nu ia valorile din yaml
    //TODO pt household
    //TODO continuat task 8
    @Value("${internationalization.country}")
    private String country="RO";
    @Value("${internationalization.lang}")
    private String lang="ro";

    private String removeWhitespaces(String str){
        str = str.replaceAll("\\s+","");
        return str;
    }
    public String translateWord(String word) {
        Locale locale = new Locale(country, lang);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        return resourceBundle.getString(removeWhitespaces(word));
    }
}
