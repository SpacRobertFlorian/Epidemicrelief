package eu.accesa.internship.epidemicrelief.utils;

import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
    //TODO nu merge nu stiu dc nu ia valorile din yaml
    //TODO continuat task 8
    @Value("${internationalization.country}")
    private String country = "EN";
    @Value("${internationalization.lang}")
    private String lang = "en";

    private final Locale locale = new Locale(country, lang);

    private String removeWhitespaces(String str) {
        str = str.replaceAll("\\s+", "");
        return str;
    }

    public String translateWord(String word) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        return resourceBundle.getString(removeWhitespaces(word));
    }

    public String getCurrency(String country) {
        Locale localeCurrency = new Locale("country", "currency");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        return resourceBundle.getString(country);
    }

    public String getCountry() {
        return country;
    }

    //convert current currency to a specific one
    public Double calculateCurrency(Double price, Currency localCurrency) {
        Locale localeCurrency = new Locale("currency", localCurrency.getCategory());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        String strCurrency = getCurrency(country);
        Double currency = Double.valueOf(resourceBundle.getString(strCurrency));
        return price * currency;
    }


}
